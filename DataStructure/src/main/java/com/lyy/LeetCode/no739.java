package com.lyy.LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class no739 {
    public static void main(String[] args) {

        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] dailied = dailyTemperatures(temperatures);
        for (int i : dailied) {
            System.out.println("i = " + i);
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
