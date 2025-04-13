package com.lyy.xfusion;

import java.util.Arrays;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i < t; i++){
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int[] cofeTimes = new int[m];
            for (int p = 0; p < m; p++) {
                cofeTimes[p] = in.nextInt();
            }

            int[][] cofeTimes2 = new int[2][m];
            for (int p = 0; p < m; p++) {
                cofeTimes2[0][p] = cofeTimes[p];
            }

            Arrays.sort(cofeTimes);


            int minTime = 0;
            int no = 0;
            for (int k = 0; k < n; k++) {
                if(cofeTimes2[1][k] != 0) continue;
                cofeTimes2[1][k] = 0;
                minTime += cofeTimes2[0][k];
            }


            System.out.println( + 1);
        }

    }
}
