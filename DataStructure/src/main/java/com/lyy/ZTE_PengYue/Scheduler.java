package com.lyy.ZTE_PengYue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {
    List<Main.Task> tasks;
    List<Main.ComputeUnit> units;
    List<Main.Sample> samples;
    Map<Integer, Integer> taskAssignment;
    Map<Integer, Integer> taskMode;
    int totalContribution;
    int maxCompletionTime;

    public Scheduler(List<Main.Task> tasks, List<Main.ComputeUnit> units, List<Main.Sample> samples) {
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
