package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ZiJuZhenDeHe_0324 {

    private static Integer n;
    private static Integer m;
    private static Integer q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] No1 = br.readLine().split(" ");

        n = Integer.parseInt(No1[0]);
        m = Integer.parseInt(No1[1]);
        q = Integer.parseInt(No1[2]);

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] No = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(No[j]);
            }
        }
//        long[] rs = new long[q];
        int[][] tmp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                tmp[i][j] = tmp[i - 1][j] + tmp[i][j - 1] - tmp[i - 1][j - 1] + arr[i - 1][j - 1];
            }
        }
        int x1,y1,x2,y2;
        while(q -- > 0) {
            String[] No = br.readLine().split(" ");
            x1 = Integer.parseInt(No[0]);
            y1 = Integer.parseInt(No[1]);
            x2 = Integer.parseInt(No[2]);
            y2 = Integer.parseInt(No[3]);
            System.out.println(tmp[x2][y2] - tmp[x1 - 1][y2] - tmp[x2][y1 - 1] + tmp[x1 - 1][y1 - 1]);
        }
    }
}
