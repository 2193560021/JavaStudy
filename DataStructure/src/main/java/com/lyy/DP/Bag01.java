package com.lyy.DP;

/**
 * 01背包问题
 */
public class Bag01 {
    public static void main(String[] args) {
        int[] wgt = {10,20,30,40,50};
        int[] val = {50,120,150,210,240};
        int cap = 50;

        int n = wgt.length;

        int[] dp = new int[cap + 1];
        for(int i = 1; i <= n; i++)
            for(int c = cap; c >= 1; c--)
                if (wgt[i - 1] <= c) dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
        System.out.println(dp[cap]);



        /*int[][] dp = new int[n + 1][cap + 1];
        for(int i = 1; i <= n; i++)
            for(int c = 1; c <= cap; c++)
                if(wgt[i - 1] > c) dp[i][c] = dp[i - 1][c];
                else dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
        System.out.println(dp[n][cap]);*/

    }
}
