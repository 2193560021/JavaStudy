package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QianZhuiHe {
    public static void main(String[] args) throws Exception {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);

        String[] no1 = br.readLine().split(" ");
        int n = Integer.parseInt(no1[0]);
        int m = Integer.parseInt(no1[1]);

        int[] arr = new int[n];

        String[] no2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(no2[i]);
        }
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++){
            result[i] = arr[i - 1] + result[i - 1];
        }
        int[] rs = new int[m];
        for (int i = 0; i < m; i++){
            String[] no = br.readLine().split(" ");
            int l = Integer.parseInt(no[0]);
            int r = Integer.parseInt(no[1]);
            rs[i] = result[r] - result[l - 1];
        }
        for (int i = 0; i < m; i++) {
            System.out.println(rs[i]);
        }

    }
}
