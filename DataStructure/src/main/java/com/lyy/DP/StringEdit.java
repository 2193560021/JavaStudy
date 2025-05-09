package com.lyy.DP;

/**
 * 输入两个字符串s 和 t，返回将 s转换为 t所需的最少编辑步数。你可以在一个字符串中进行三种编辑操作：插入一个字符、删除一个字符、将字符替换为任意一个字符。
 */
public class StringEdit {
    public static void main(String[] args) {
        String s = "bag";
        String t = "pack";

        int sLen = s.length();
        int tLen = t.length();

        int[][] dp = new int[sLen + 1][ tLen + 1];
        for (int i = 1; i <= tLen; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i <= sLen; i++)
            for(int j = 1; j <= tLen; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
            }

        System.out.println(dp[sLen][tLen]);
    }
}
