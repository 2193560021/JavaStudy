package com.lyy.DP;

/**
 * 零钱兑换问题
 * 给定n种硬币，第i种硬币的面值为coins[i一1]，目标金额为amt，每种硬币可以重复选取，问能够凑出目标金
 * 额的最少硬币数量。如果无法凑出目标金额，则返回一1。
 */
public class Money {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amt = 18;
        int MAX = amt + 1;
        int n = coins.length;

        int[] dp = new int[amt + 1];
        for(int i = 0; i <= amt; i++) dp[i] = MAX;
        dp[0] = 0;
        for(int i = 1; i <= n; i++)
            for(int a = 1; a <= amt; a++){
                if(coins[i - 1] <= a){
                    dp[a] = Math.min(dp[a], dp[a - coins[i - 1]] + 1);
                }
            }
        System.out.println(dp[amt] == MAX ? -1 : dp[amt]);


        /*int[][] dp = new int[n + 1][amt + 1];
        for(int i = 0; i <= amt; i++) dp[0][i] = MAX; //初始化首行为amt + 1
        for(int i = 1; i <= n; i++)
            for(int a = 1; a <= amt; a++){
                if(coins[i - 1] > a) dp[i][a] = dp[i - 1][a];
                else dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
            }
        System.out.println(dp[n][amt] == MAX ? -1 : dp[n][amt]);*/
    }
}
