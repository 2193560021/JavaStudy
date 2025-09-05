package com.lyy.LeetCode;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class no739 {
    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Semaphore semaphore = new Semaphore(1);
        StampedLock stampedLock = new StampedLock();
        new ArrayBlockingQueue<Integer>(10);
        LockSupport.park();
        new Hashtable<Integer, Integer>();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        LockSupport.unpark(Thread.currentThread());
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadMXBean.findDeadlockedThreads();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] dailied = dailyTemperatures(temperatures);
        for (int i : dailied) {
            System.out.println("i = " + i);
        }

        while (true) {
            int i = 1;
        }
    }

    public static int[] dailyTemperatures(int[] temperatures) {

        int len = temperatures.length;
        int[] ans = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int popIdx = stack.pop();
                ans[popIdx] = i - popIdx;
            }
            stack.push(i);
        }
        return ans;

    }
}
