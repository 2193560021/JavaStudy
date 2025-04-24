package com.lyy.LeetCode;

public class no279 {
    public static void main(String[] args) {
        int n = 24;
        int[] res = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 1; j * j <= i; j++){
                min = Math.min(min, res[i - j * j]);
            }
            res[i] = min +  1;
        }

        System.out.println(res[n]);
    }
}
