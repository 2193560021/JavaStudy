package com.lyy.DP;

import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 完全背包问题
 */
public class BagAll {
    public static void main(String[] args) {
        Thread thread = new Thread();
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
        FutureTask<Integer> f = new FutureTask<>(callable);
        Thread thread1 = new Thread(f);
        thread1.start();
        int[] wgt = {10,20,30,40,50};//重量
        int[] val = {50,120,150,210,240};//价值
        int cap = 50;

        int n = wgt.length;
        int[] dp = new int[cap + 1];
        for(int i = 1; i <= n; i++)
            for(int c = 1; c <= cap; c++){
                if (wgt[i - 1] <= c) dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
            }

        System.out.println(dp[cap]);

        /*int[][] dp = new int[n + 1][cap + 1];
        for(int i = 1; i <= n; i++)
            for(int c = 1; c <= cap; c++){
                if(wgt[i - 1] > c) dp[i][c] = dp[i - 1][c];
                else dp[i][c] = Math.max(dp[i - 1][c], dp[i][c - wgt[i - 1]] + val[i - 1]);
            }
        System.out.println(dp[n][cap]);*/

    }
}
