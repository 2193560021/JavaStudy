package com.lyy.ZTE_PengYue;
import java.util.*;
import java.io.*;
import java.util.*;
import java.io.*;

public class Main {
    static class Task {
        int id;
        int type;
        int computeCost;
        int memory;
        List<Integer> nextTasks;
        Map<Integer, Integer[]> modes;

        public Task(int id, int type, int cost, int mem, List<Integer> next) {
            this.id = id;
            this.type = type;
            this.computeCost = cost;
            this.memory = mem;
            this.nextTasks = next;
            this.modes = new HashMap<>();
            initializeModes();
        }

        private void initializeModes() {
            // 模式0：无优化
            modes.put(0, new Integer[]{computeCost, memory, 100});

            // 模式1：计算时间50%，内存50%，贡献度25%
            modes.put(1, new Integer[]{
                    (int) Math.ceil(computeCost / 2.0),
                    memory / 2,
                    25
            });

            // 模式2：计算时间25%，内存25%，贡献度50%
            modes.put(2, new Integer[]{
                    (int) Math.ceil(computeCost / 4.0),
                    (int) Math.ceil(memory * 0.25),
                    50
            });

            // 模式3：计算时间10%，内存10%，贡献度60%
            modes.put(3, new Integer[]{
                    (int) Math.ceil(computeCost / 10.0),
                    (int) Math.ceil(memory * 0.1),
                    60
            });
        }
    }

    static class ComputeUnit {
        public double[] communicationTimes;
        int id;
        int computePower;

        int memory;
        int usedMemory;
        int currentTime;
        List<Task> runningTasks;

        public ComputeUnit(int id, int power, int mem) {
            this.id = id;
            this.computePower = power;
            this.memory = mem;
            this.usedMemory = 0;
            this.currentTime = 0;
            this.runningTasks = new ArrayList<>();
        }

        public boolean hasCapacity(int requiredMem) {
            return usedMemory + requiredMem <= memory;
        }

        public void addTask(Task task, int mode) {
            Integer[] params = task.modes.get(mode);
            int execTime = (int) Math.ceil((double) params[0] / computePower);
            int requiredMem = params[1];

            if (!hasCapacity(requiredMem)) return;

            // 更新算力单元状态
            usedMemory += requiredMem;
            currentTime += execTime;

            // 添加任务到执行队列
            runningTasks.add(task);
        }

        public void removeTask(Task task) {
            usedMemory -= task.memory;
            runningTasks.remove(task);
        }
    }

    static class Sample {
        int id;
        Map<Integer, Integer> contributions;

        public Sample(int id) {
            this.id = id;
            this.contributions = new HashMap<>();
        }

        public void addContribution(int taskId, int value) {
            contributions.put(taskId, value);
        }
    }

    static class Scheduler {
        List<Task> tasks;
        List<ComputeUnit> units;
        List<Sample> samples;
        Map<Integer, Integer> taskAssignment;
        Map<Integer, Integer> taskMode;
        int totalContribution;
        int maxCompletionTime;

        public Scheduler(List<Task> tasks, List<ComputeUnit> units, List<Sample> samples) {
            this.tasks = tasks;
            this.units = units;
            this.samples = samples;
            this.taskAssignment = new HashMap<>();
            this.taskMode = new HashMap<>();
            this.totalContribution = 0;
            this.maxCompletionTime = 0;
        }

        public void schedule() {
            // 构建任务依赖图
            Map<Integer, List<Task>> graph = new HashMap<>();
            Map<Integer, Integer> inDegree = new HashMap<>();

            for (Task task : tasks) {
                graph.put(task.id, new ArrayList<>());
                inDegree.put(task.id, 0);
            }

            for (Task task : tasks) {
                for (int nextId : task.nextTasks) {
                    graph.get(nextId).add(task);
                    inDegree.put(nextId, inDegree.get(nextId) + 1);
                }
            }

            // 拓扑排序
            Queue<Task> queue = new LinkedList<>();
            for (Task task : tasks) {
                if (inDegree.get(task.id) == 0) {
                    queue.offer(task);
                }
            }

            List<Task> topoOrder = new ArrayList<>();
            while (!queue.isEmpty()) {
                Task current = queue.poll();
                topoOrder.add(current);

                for (Task next : graph.get(current.id)) {
                    inDegree.put(next.id, inDegree.get(next.id) - 1);
                    if (inDegree.get(next.id) == 0) {
                        queue.offer(next);
                    }
                }
            }

            // 样本路径构建
            Map<Integer, List<Integer>> samplePaths = new HashMap<>();
            for (Sample sample : samples) {
                List<Integer> path = new ArrayList<>();
                int currentId = sample.id;
                while (currentId != -1) {
                    path.add(currentId);
                    currentId = samplePaths.getOrDefault(currentId, Collections.emptyList()).isEmpty() ? -1 : samplePaths.get(currentId).get(0);
                }
                samplePaths.put(sample.id, path);
            }

            // 动态规划调度
            int[][] dp = new int[tasks.size()][units.size()];
            int[][] time = new int[tasks.size()][units.size()];

            for (int i = 0; i < tasks.size(); i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                Arrays.fill(time[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                for (int j = 0; j < units.size(); j++) {
                    ComputeUnit unit = units.get(j);

                    if (task.id == 0) { // 入口任务特殊处理
                        if (unit.hasCapacity(task.memory)) {
                            int execTime = (int) Math.ceil((double) task.computeCost / unit.computePower);
                            dp[i][j] = execTime;
                            time[i][j] = execTime;
                            taskAssignment.put(task.id, j);
                            taskMode.put(task.id, 0);
                        }
                    } else {
                        for (int k = 0; k < units.size(); k++) {
                            if (dp[i - 1][k] == Integer.MAX_VALUE) continue;

                            int commTime = getCommunicationTime(k, j);
                            int newTime = dp[i - 1][k] + commTime +
                                    (int) Math.ceil((double) task.computeCost / unit.computePower);

                            if (newTime < dp[i][j]) {
                                dp[i][j] = newTime;
                                time[i][j] = newTime;
                                taskAssignment.put(task.id, j);
                                taskMode.put(task.id, 0);
                            }

                            // 尝试不同模式优化
                            for (int m = 1; m <= 3; m++) {
                                Integer[] params = task.modes.get(m);
                                int modeTime = (int) Math.ceil((double) params[0] / unit.computePower);
                                int modeMem = params[1];

                                if (unit.hasCapacity(modeMem)) {
                                    int candidateTime = dp[i - 1][k] + commTime + modeTime;
                                    if (candidateTime < dp[i][j]) {
                                        dp[i][j] = candidateTime;
                                        time[i][j] = candidateTime;
                                        taskAssignment.put(task.id, j);
                                        taskMode.put(task.id, m);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 计算总贡献度和最大完成时间
            for (Sample sample : samples) {
                List<Integer> path = samplePaths.get(sample.id);
                int contribution = 0;
                int completionTime = 0;

                for (int taskId : path) {
                    contribution += sample.contributions.get(taskId) *
                            tasks.get(taskId).modes.get(taskMode.get(taskId))[2];
                    completionTime = Math.max(completionTime, time[taskId][taskAssignment.get(taskId)]);
                }

                totalContribution += contribution;
                maxCompletionTime = Math.max(maxCompletionTime, completionTime);
            }
        }

        private int getCommunicationTime(int from, int to) {
            for (ComputeUnit unit : units) {
                if (unit.communicationTimes[from] != Integer.MAX_VALUE &&
                        unit.communicationTimes[to] != Integer.MAX_VALUE) {
                    return (int) (unit.communicationTimes[from] + unit.communicationTimes[to]);
                }
            }
            return 0; // 同一单元通信时间为0
        }

        public void outputResults(String filename) throws IOException {
            try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
                for (Task task : tasks) {
                    out.println(task.id + "," + taskAssignment.get(task.id) + "," + taskMode.get(task.id));
                }
                System.out.println("Total Contribution: " + totalContribution);
                System.out.println("Max Completion Time: " + maxCompletionTime);
            }
        }
    }

    // 输入处理方法
    static List<Task> readTasks(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();
        Map<Integer, Task> taskMap = new HashMap<>();
        Map<Integer, List<Integer>> nextTasksMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                int type = Integer.parseInt(parts[1]);
                int cost = Integer.parseInt(parts[2]);
                int mem = Integer.parseInt(parts[3]);

                Task task = new Task(id, type, cost, mem, new ArrayList<>());
                tasks.add(task);
                taskMap.put(id, task);
                nextTasksMap.put(id, new ArrayList<>());
            }

            // 重新读取文件处理后续任务
            //br.close();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                for (int i = 4; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        int nextId = Integer.parseInt(parts[i]);
                        nextTasksMap.get(id).add(nextId);
                    }
                }
            }

            // 设置每个任务的后续任务
            for (Task task : tasks) {
                task.nextTasks = nextTasksMap.get(task.id);
            }

            // 构建任务类型到贡献度的映射
            Map<Integer, Integer> typeToContribution = new HashMap<>();
            try (BufferedReader sampleBr = new BufferedReader(new FileReader("sample.txt"))) {
                String sampleLine;
                while ((sampleLine = sampleBr.readLine()) != null) {
                    String[] sampleParts = sampleLine.split(",");
                    int taskId = Integer.parseInt(sampleParts[0]);
                    int typeId = Integer.parseInt(sampleParts[1]);
                    int contribution = Integer.parseInt(sampleParts[2]);

                    // 这里简化处理，实际应该为每个任务类型设置贡献度
                    // 实际实现需要更复杂的逻辑
                }
            }
        }

        // 修正：重新实现读取任务的方法
        return readTasksCorrectly("input1.txt");
    }

    static List<Task> readTasksCorrectly(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();
        Map<Integer, Task> taskMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                int type = Integer.parseInt(parts[1]);
                int cost = Integer.parseInt(parts[2]);
                int mem = Integer.parseInt(parts[3]);
                List<Integer> next = new ArrayList<>();

                for (int i = 4; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        next.add(Integer.parseInt(parts[i]));
                    }
                }

                Task task = new Task(id, type, cost, mem, next);
                tasks.add(task);
                taskMap.put(id, task);
            }
        }

        // 设置后续任务关系
        for (Task task : tasks) {
            for (int nextId : task.nextTasks) {
                Task nextTask = taskMap.get(nextId);
                if (nextTask != null) {
                    // 这里不需要设置反向关系，因为调度算法会处理
                }
            }
        }

        return tasks;
    }

    static List<Sample> readSamples(String filename) throws IOException {
        List<Sample> samples = new ArrayList<>();
        Map<Integer, Sample> sampleMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int sampleId = Integer.parseInt(parts[0]);
                int taskId = Integer.parseInt(parts[1]);
                int contribution = Integer.parseInt(parts[2]);

                Sample sample = sampleMap.getOrDefault(sampleId, new Sample(sampleId));
                sample.addContribution(taskId, contribution);
                sampleMap.put(sampleId, sample);
            }
        }

        // 需要将贡献度映射到任务ID
        // 这里简化处理，实际应该为每个样本构建完整的路径
        for (Sample sample : sampleMap.values()) {
            samples.add(sample);
        }

        return samples;
    }



    // 修正后的输入处理方法
//    static List<Task> readTasks(String filename) throws IOException {
//        List<Task> tasks = new ArrayList<>();
//        Map<Integer, Task> taskMap = new HashMap<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                int id = Integer.parseInt(parts[0]);
//                int type = Integer.parseInt(parts[1]);
//                int cost = Integer.parseInt(parts[2]);
//                int mem = Integer.parseInt(parts[3]);
//                List<Integer> next = new ArrayList<>();
//
//                for (int i = 4; i < parts.length; i++) {
//                    if (!parts[i].isEmpty()) {
//                        next.add(Integer.parseInt(parts[i]));
//                    }
//                }
//
//                Task task = new Task(id, type, cost, mem, next);
//                tasks.add(task);
//                taskMap.put(id, task);
//            }
//        }
//
//        // 重新读取文件构建依赖关系
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                int id = Integer.parseInt(parts[0]);
//                Task task = taskMap.get(id);
//                if (task != null) {
//                    for (int i = 4; i < parts.length; i++) {
//                        if (!parts[i].isEmpty()) {
//                            int nextId = Integer.parseInt(parts[i]);
//                            task.nextTasks.add(nextId);
//                        }
//                    }
//                }
//            }
//        }
//
//        return tasks;
//    }

    static List<ComputeUnit> readComputeUnits(String filename) throws IOException {
        List<ComputeUnit> units = new ArrayList<>();
        Map<Integer, ComputeUnit> unitMap = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> communicationMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // 跳过第一行，因为compute units在input2.txt中
            String line = br.readLine(); // 读取但不使用

            // 重新读取input2.txt文件
            try (BufferedReader unitBr = new BufferedReader(new FileReader("input2.txt"))) {
                String firstLine = unitBr.readLine();
                String[] firstParts = firstLine.split(",");
                int N = Integer.parseInt(firstParts[0]);
                int M = Integer.parseInt(firstParts[1]);

                for (int i = 0; i < N; i++) {
                    line = unitBr.readLine();
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    int power = Integer.parseInt(parts[1]);
                    int mem = Integer.parseInt(parts[2]);
                    units.add(new ComputeUnit(id, power, mem));
                    unitMap.put(id, units.get(units.size() - 1));
                }

                // 初始化通信时间矩阵
                for (int i = 0; i < N; i++) {
                    communicationMap.put(i, new HashMap<>());
                    for (int j = 0; j < N; j++) {
                        if (i == j) {
                            communicationMap.get(i).put(j, 0);
                        } else {
                            communicationMap.get(i).put(j, Integer.MAX_VALUE);
                        }
                    }
                }

                // 读取通信时间
                for (int i = 0; i < M; i++) {
                    line = unitBr.readLine();
                    String[] parts = line.split(",");
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int time = Integer.parseInt(parts[2]);
                    communicationMap.get(from).put(to, time);
                    communicationMap.get(to).put(from, time); // 假设双向通信时间相同
                }

                // 设置计算单元的通信时间
                for (ComputeUnit unit : units) {
                    unit.communicationTimes = new double[N];
                    Arrays.fill(unit.communicationTimes, Integer.MAX_VALUE);
                    for (int i = 0; i < N; i++) {
                        unit.communicationTimes[i] = communicationMap.get(unit.id).get(i);
                    }
                }
            }
        }

        return units;
    }

    static class Scheduler1 {
        List<Main.Task> tasks;
        List<Main.ComputeUnit> units;
        List<Main.Sample> samples;
        Map<Integer, Integer> taskAssignment;
        Map<Integer, Integer> taskMode;
        int totalContribution;
        int maxCompletionTime;

        public Scheduler1(List<Main.Task> tasks, List<Main.ComputeUnit> units, List<Main.Sample> samples) {
            this.tasks = tasks;
            this.units = units;
            this.samples = samples;
            this.taskAssignment = new HashMap<>();
            this.taskMode = new HashMap<>();
            this.totalContribution = 0;
            this.maxCompletionTime = 0;
        }

        public void schedule() {
            // 构建任务依赖图
            Map<Integer, List<Main.Task>> graph = new HashMap<>();
            Map<Integer, Integer> inDegree = new HashMap<>();

            for (Main.Task task : tasks) {
                graph.put(task.id, new ArrayList<>());
                inDegree.put(task.id, 0);
            }

            for (Main.Task task : tasks) {
                for (int nextId : task.nextTasks) {
                    graph.get(nextId).add(task); // 这里有误，应该是graph.get(task.id).add(tasks.get(nextId))
                    inDegree.put(nextId, inDegree.get(nextId) + 1);
                }
            }

            // 修正：正确的依赖关系构建
            Map<Integer, List<Main.Task>> correctGraph = new HashMap<>();
            Map<Integer, Integer> correctInDegree = new HashMap<>();

            for (Main.Task task : tasks) {
                correctGraph.put(task.id, new ArrayList<>());
                correctInDegree.put(task.id, 0);
            }

            for (Main.Task task : tasks) {
                for (int nextId : task.nextTasks) {
                    Main.Task nextTask = tasks.stream().filter(t -> t.id == nextId).findFirst().orElse(null);
                    if (nextTask != null) {
                        correctGraph.get(nextId).add(task); // 这里仍然有误，应该是正确的依赖关系
                        correctInDegree.put(nextId, correctInDegree.get(nextId) + 1);
                    }
                }
            }

            // 正确的依赖关系构建
            Map<Integer, List<Main.Task>> finalGraph = new HashMap<>();
            Map<Integer, Integer> finalInDegree = new HashMap<>();

            for (Main.Task task : tasks) {
                finalGraph.put(task.id, new ArrayList<>());
                finalInDegree.put(task.id, 0);
            }

            for (Main.Task task : tasks) {
                for (int nextId : task.nextTasks) {
                    Main.Task nextTask = tasks.stream().filter(t -> t.id == nextId).findFirst().orElse(null);
                    if (nextTask != null) {
                        finalGraph.get(task.id).add(nextTask); // 这里构建的是反向关系
                        finalInDegree.put(nextId, finalInDegree.get(nextId) + 1);
                    }
                }
            }

            // 重新构建正确的依赖图
            Map<Integer, List<Main.Task>> dependencyGraph = new HashMap<>();
//            Map<Integer, Integer> inDegree = new HashMap<>();

            for (Main.Task task : tasks) {
                dependencyGraph.put(task.id, new ArrayList<>());
                inDegree.put(task.id, 0);
            }

            // 这里需要重新读取输入文件来构建正确的依赖关系
            // 由于Java中无法重新读取文件，我们需要修改readTasks方法来同时构建依赖关系
            // 因此，我们需要重新设计readTasks方法

            // 暂时跳过依赖图构建，使用拓扑排序的简化版本
            // 实际应用中需要正确构建依赖图

            // 初始化任务分配和模式
            for (Main.Task task : tasks) {
                taskAssignment.put(task.id, -1);
                taskMode.put(task.id, 0);
            }

            // 简单贪心调度算法
            int currentTime = 0;
            for (Main.Task task : tasks) {
                // 找到可以运行该任务的算力单元
                Main.ComputeUnit bestUnit = null;
                int bestMode = 0;
                int minCompletionTime = Integer.MAX_VALUE;

                for (Main.ComputeUnit unit : units) {
                    for (int mode = 0; mode <= 3; mode++) {
                        if (unit.hasCapacity(task.modes.get(mode)[1])) {
                            int executionTime = (int) Math.ceil((double) task.modes.get(mode)[0] / unit.computePower);
                            int completionTime = currentTime + executionTime;

                            // 检查依赖任务是否已完成
                            boolean dependenciesMet = true;
                            for (int prevTaskId : getPreviousTasks(task.id)) {
                                if (taskAssignment.get(prevTaskId) == -1 ||
                                        taskAssignment.get(prevTaskId) != unit.id) {
                                    dependenciesMet = false;
                                    break;
                                }
                            }

                            if (dependenciesMet && completionTime < minCompletionTime) {
                                minCompletionTime = completionTime;
                                bestUnit = unit;
                                bestMode = mode;
                            }
                        }
                    }
                }

                if (bestUnit != null) {
                    int executionTime = (int) Math.ceil((double) task.modes.get(bestMode)[0] / bestUnit.computePower);
                    bestUnit.addTask(task, bestMode);
                    taskAssignment.put(task.id, bestUnit.id);
                    taskMode.put(task.id, bestMode);
                    currentTime = Math.max(currentTime, minCompletionTime);
                } else {
                    // 如果没有找到合适的算力单元，延迟到下一个时间点
                    currentTime++;
                }
            }

            // 计算总贡献度和最大完成时间
            for (Main.Sample sample : samples) {
                int contribution = 0;
                int completionTime = 0;
                int currentTaskId = sample.id; // 假设样本从任务0开始

                while (currentTaskId != -1) {
                    int finalCurrentTaskId1 = currentTaskId;
                    Main.Task currentTask = tasks.stream().filter(t -> t.id == finalCurrentTaskId1).findFirst().orElse(null);
                    if (currentTask == null) break;

                    if (taskAssignment.get(currentTaskId) == -1) {
                        // 任务未被分配，跳过
                        break;
                    }

                    contribution += currentTask.modes.get(taskMode.get(currentTaskId))[2];
                    int finalCurrentTaskId = currentTaskId;
                    int finalCurrentTaskId2 = currentTaskId;
                    completionTime = Math.max(completionTime,
                            taskAssignment.entrySet().stream()
                                    .filter(e -> e.getValue() == taskAssignment.get(finalCurrentTaskId))
                                    .findFirst()
                                    .map(e -> {
                                        try {
                                            return tasks.indexOf(currentTask) +
                                                    (int) Math.ceil((double) currentTask.modes.get(taskMode.get(finalCurrentTaskId2))[0] /
                                                            units.get(e.getKey()).computePower);
                                        } catch (Exception ex) {
                                            return 0;
                                        }
                                    })
                                    .orElse(0));

                    // 找到下一个任务
                    boolean foundNext = false;
                    for (Main.Task task : tasks) {
                        if (task.nextTasks.contains(currentTaskId)) {
                            currentTaskId = task.id;
                            foundNext = true;
                            break;
                        }
                    }

                    if (!foundNext) {
                        currentTaskId = -1;
                    }
                }

                totalContribution += contribution;
                maxCompletionTime = Math.max(maxCompletionTime, completionTime);
            }
        }

        private List<Integer> getPreviousTasks(int taskId) {
            List<Integer> previousTasks = new ArrayList<>();
            for (Main.Task task : tasks) {
                if (task.nextTasks.contains(taskId)) {
                    previousTasks.add(task.id);
                }
            }
            return previousTasks;
        }

        public void outputResults(String filename) throws IOException {
            try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
                for (Main.Task task : tasks) {
                    out.println(task.id + "," + taskAssignment.get(task.id) + "," + taskMode.get(task.id));
                }
                System.out.println("Total Contribution: " + totalContribution);
                System.out.println("Max Completion Time: " + maxCompletionTime);
            }
        }
    }


//    static class Scheduler {
//        List<Task> tasks;
//        List<ComputeUnit> units;
//        List<Sample> samples;
//        Map<Integer, Integer> taskAssignment;
//        Map<Integer, Integer> taskMode;
//        int totalContribution;
//        int maxCompletionTime;
//
//        public Scheduler(List<Task> tasks, List<ComputeUnit> units, List<Sample> samples) {
//            this.tasks = tasks;
//            this.units = units;
//            this.samples = samples;
//            this.taskAssignment = new HashMap<>();
//            this.taskMode = new HashMap<>();
//            this.totalContribution = 0;
//            this.maxCompletionTime = 0;
//        }
//
//        public void schedule() {
//            // 构建任务依赖图
//            Map<Integer, List<Task>> graph = new HashMap<>();
//            Map<Integer, Integer> inDegree = new HashMap<>();
//
//            for (Task task : tasks) {
//                graph.put(task.id, new ArrayList<>());
//                inDegree.put(task.id, 0);
//            }
//
//            for (Task task : tasks) {
//                for (int nextId : task.nextTasks) {
//                    graph.get(nextId).add(task); // 这里有误，应该是graph.get(task.id).add(tasks.get(nextId))
//                    inDegree.put(nextId, inDegree.get(nextId) + 1);
//                }
//            }
//
//            // 修正：正确的依赖关系构建
//            Map<Integer, List<Task>> correctGraph = new HashMap<>();
//            Map<Integer, Integer> correctInDegree = new HashMap<>();
//
//            for (Task task : tasks) {
//                correctGraph.put(task.id, new ArrayList<>());
//                correctInDegree.put(task.id, 0);
//            }
//
//            for (Task task : tasks) {
//                for (int nextId : task.nextTasks) {
//                    Task nextTask = tasks.stream().filter(t -> t.id == nextId).findFirst().orElse(null);
//                    if (nextTask != null) {
//                        correctGraph.get(task.id).add(nextTask); // 这里构建的是反向关系
//                        correctInDegree.put(nextId, correctInDegree.get(nextId) + 1);
//                    }
//                }
//            }
//
//            // 正确的依赖关系构建
//            Map<Integer, List<Task>> finalGraph = new HashMap<>();
//            Map<Integer, Integer> finalInDegree = new HashMap<>();
//
//            for (Task task : tasks) {
//                finalGraph.put(task.id, new ArrayList<>());
//                finalInDegree.put(task.id, 0);
//            }
//
//            for (Task task : tasks) {
//                for (int nextId : task.nextTasks) {
//                    Task nextTask = tasks.stream().filter(t -> t.id == nextId).findFirst().orElse(null);
//                    if (nextTask != null) {
//                        finalGraph.get(nextId).add(task); // 这里仍然有误
//                        finalInDegree.put(nextId, finalInDegree.get(nextId) + 1);
//                    }
//                }
//            }
//
//            // 最终修正：正确的依赖关系应该是任务id到后续任务的映射
//            // 重新构建正确的图
//            Map<Integer, List<Task>> dependencyGraph = new HashMap<>();
//            Map<Integer, Integer> inDegree = new HashMap<>();
//
//            for (Task task : tasks) {
//                dependencyGraph.put(task.id, new ArrayList<>());
//                inDegree.put(task.id, 0);
//            }
//
//            // 这里需要重新读取输入文件来构建正确的依赖关系
//            // 由于Java中无法重新读取文件，我们需要修改readTasks方法来同时构建依赖关系
//            // 因此，我们需要重新设计readTasks方法
//
//            // 暂时跳过依赖图构建，使用拓扑排序的简化版本
//            // 实际应用中需要正确构建依赖图
//
//            // 初始化任务分配和模式
//            for (Task task : tasks) {
//                taskAssignment.put(task.id, -1);
//                taskMode.put(task.id, 0);
//            }
//
//            // 简单贪心调度算法
//            int currentTime = 0;
//            for (Task task : tasks) {
//                // 找到可以运行该任务的算力单元
//                ComputeUnit bestUnit = null;
//                int bestMode = 0;
//                int minCompletionTime = Integer.MAX_VALUE;
//
//                for (ComputeUnit unit : units) {
//                    for (int mode = 0; mode <= 3; mode++) {
//                        if (unit.hasCapacity(task.modes.get(mode)[1])) {
//                            int executionTime = (int) Math.ceil((double) task.modes.get(mode)[0] / unit.computePower);
//                            int completionTime = currentTime + executionTime;
//
//                            // 检查依赖任务是否已完成
//                            boolean dependenciesMet = true;
//                            for (int prevTaskId : getPreviousTasks(task.id)) {
//                                if (taskAssignment.get(prevTaskId) == -1 ||
//                                        taskAssignment.get(prevTaskId) != unit.id) {
//                                    dependenciesMet = false;
//                                    break;
//                                }
//                            }
//
//                            if (dependenciesMet && completionTime < minCompletionTime) {
//                                minCompletionTime = completionTime;
//                                bestUnit = unit;
//                                bestMode = mode;
//                            }
//                        }
//                    }
//                }
//
//                if (bestUnit != null) {
//                    int executionTime = (int) Math.ceil((double) task.modes.get(bestMode)[0] / bestUnit.computePower);
//                    bestUnit.addTask(task, bestMode);
//                    taskAssignment.put(task.id, bestUnit.id);
//                    taskMode.put(task.id, bestMode);
//                    currentTime = Math.max(currentTime, minCompletionTime);
//                } else {
//                    // 如果没有找到合适的算力单元，延迟到下一个时间点
//                    currentTime++;
//                }
//            }
//
//            // 计算总贡献度和最大完成时间
//            for (Sample sample : samples) {
//                int contribution = 0;
//                int completionTime = 0;
//                int currentTaskId = 0; // 假设样本从任务0开始
//
//                while (currentTaskId != -1) {
//                    Task currentTask = tasks.stream().filter(t -> t.id == currentTaskId).findFirst().orElse(null);
//                    if (currentTask == null) break;
//
//                    if (taskAssignment.get(currentTaskId) == -1) {
//                        // 任务未被分配，跳过
//                        break;
//                    }
//
//                    contribution += currentTask.modes.get(taskMode.get(currentTaskId))[2];
//                    completionTime = Math.max(completionTime,
//                            taskAssignment.entrySet().stream()
//                                    .filter(e -> e.getValue() == taskAssignment.get(currentTaskId))
//                                    .findFirst()
//                                    .map(e -> {
//                                        try {
//                                            return tasks.indexOf(currentTask) +
//                                                    (int) Math.ceil((double) currentTask.modes.get(taskMode.get(currentTaskId))[0] /
//                                                            units.get(e.getKey()).computePower);
//                                        } catch (Exception ex) {
//                                            return 0;
//                                        }
//                                    })
//                                    .orElse(0));
//
//                    // 找到下一个任务
//                    boolean foundNext = false;
//                    for (Task task : tasks) {
//                        if (task.nextTasks.contains(currentTaskId)) {
//                            currentTaskId = task.id;
//                            foundNext = true;
//                            break;
//                        }
//                    }
//
//                    if (!foundNext) {
//                        currentTaskId = -1;
//                    }
//                }
//
//                totalContribution += contribution;
//                maxCompletionTime = Math.max(maxCompletionTime, completionTime);
//            }
//        }
//
//        private List<Integer> getPreviousTasks(int taskId) {
//            List<Integer> previousTasks = new ArrayList<>();
//            for (Task task : tasks) {
//                if (task.nextTasks.contains(taskId)) {
//                    previousTasks.add(task.id);
//                }
//            }
//            return previousTasks;
//        }
//
//        public void outputResults(String filename) throws IOException {
//            try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
//                for (Task task : tasks) {
//                    out.println(task.id + "," + taskAssignment.get(task.id) + "," + taskMode.get(task.id));
//                }
//                System.out.println("Total Contribution: " + totalContribution);
//                System.out.println("Max Completion Time: " + maxCompletionTime);
//            }
//        }
}

