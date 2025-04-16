package com.lyy.LeetCode;

public class no79_01 {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "SEE";

        System.out.println(exist(board, word));

    }
    private static final int[][] DIRS = {{0,-1}, {0,1}, {-1,0}, {1,0}};

    public static boolean exist(char[][] board, String word) {
        int[] cnt = new int[128];
        for(char[] row : board){
            for(char c : row){
                cnt[c]++;
            }
        }

        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for(char c : w){
            if(++wordCnt[c] > cnt[c]){
                return false;
            }
        }

        if(cnt[w[w.length - 1]] < cnt[w[0]]){
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(dfs(i, j, 0, board, w)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int i, int j, int k, char[][] board, char[] word){
        if(board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        board[i][j] = 0;
        for(int[] d : DIRS){
            int x = i + d[0];
            int y = j + d[1];
            if(0 <= x && x < board.length && 0<= y && y < board[x].length && dfs(x, y, k+1, board, word)){
                return true;
            }
        }
        board[i][j] = word[k];
        return false;
    }
}
