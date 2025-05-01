package com.lyy.DP;

/**
 * 矩阵最短路径长度
 */
public class JuZhenZuiDuan {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1, 5},
                {2, 2, 4, 2},
                {5, 3, 2, 1},
                {4, 3, 5, 2}
        };


        int n = grid.length;
        int m = grid[0].length;

        int[] dp = new int[m];
        dp[0] = grid[0][0];

        for(int i = 1; i < m; i++)
            dp[i] = dp[i - 1] + grid[0][i];

        for(int i = 1; i < n; i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j = 1; j < m; j++){
                dp[j] = Math.min(dp[j - 1],dp[j]) + grid[i][j];
            }
        }
        System.out.println(dp[m - 1]);



        /*int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {   //初始化首行
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {   //初始化首列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++)
            for(int j = 1; j < m; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        System.out.println(dp[n - 1][m - 1]);*/
        return;
    }

}
