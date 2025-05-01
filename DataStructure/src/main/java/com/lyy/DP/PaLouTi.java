package com.lyy.DP;

/**
 * 爬楼梯
 */
public class PaLouTi {
    public static void main(String[] args) {
        int n = 5;
        int[] cost = {0,1,3,4,2,1};
        int dp = plt(n);
        int dp1 = plt_DaiJia(n, cost);
        System.out.println("方案总数：" + dp1);
    }

    /**
     * 代价版
     * @param n
     * @param cost
     * @return
     */
    public static int plt_DaiJia(int n, int[] cost){
        /*int[] dp = new int[n + 1];
        dp[1] = cost[0];
        dp[2] = cost[1];
        for(int i = 3; i <= n; i++){
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i - 1];
        }
        return dp[n];*/

//        int[] dp = new int[n + 1];
        int a = cost[1];
        int b = cost[2];
        for(int i = 3; i <= n; i++){
            int tmp = b;
            b = Math.min(a, tmp) + cost[i];
            a = tmp;
        }
        return b;

    }

    public static int plt(int n){
        /*
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
        */

        int a = 1;
        int b = 2;
        for(int i = 3; i <= n; i++){
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }
}
