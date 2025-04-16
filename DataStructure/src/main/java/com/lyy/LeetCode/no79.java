package com.lyy.LeetCode;

public class no79 {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = "SEE";

        System.out.println(exist(board, word));

    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                boolean flag = check(board, visited, i, j, word, 0);
                if(flag) return true;
            }
        }

        return false;

    }

    private static boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if(board[i][j] != word.charAt(k)) return false;
        else if (k == word.length() - 1) return true;

        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0,-1}, {1, 0}, {-1, 0}};
        boolean res = false;
        for (int[] dir : directions) {
            int newi = i + dir[0];
            int newj = j + dir[1];
            if(newi >= 0 && newj >= 0 && newi < board.length && newj < board[0].length){
                if(!visited[newi][newj]){
                    boolean check = check(board, visited, newi, newj, word, k + 1);
                    if(check){
                        res = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;

        return res;


    }
}
