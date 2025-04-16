package com.lyy.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class no131 {
    public static void main(String[] args) {
        List<List<String>> aab = partition("aab");
        aab.forEach(System.out::println);
    }

    static boolean[][] f;
    static List<List<String>> res = new ArrayList<>();
    static List<String> tmp = new ArrayList<>();
    static int n;

    public static List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(f[i] ,true);
        }

        for (int i = n - 1; i >= 0; --i)
            for (int j = i + 1; j < n; ++j){
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        dfs(s, 0);
        return res;
    }

    static void dfs(String s, int i){
        if(i == n){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = i; j < n; j++){
            if(f[i][j]){
                tmp.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                tmp.remove(tmp.size() - 1);

            }
        }
    }

}
