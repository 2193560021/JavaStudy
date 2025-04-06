package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No800_YuanSuMuBiaoHe {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] no1 = br.readLine().split(" ");
        int n = Integer.parseInt(no1[0]);
        int m = Integer.parseInt(no1[1]);
        int x = Integer.parseInt(no1[2]);
        int[] a = new int[n];
        int[] b = new int[m];
        String[] no2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(no2[i]);
        }
        String[] no3 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(no3[i]);
        }

        for (int p = 0, q = m - 1; p < n; p++){
            while(q >= 0 && a[p] + b[q] > x) q--;
            if(a[p] + b[q] == x){
                System.out.println(p + " " + q);
                break;
            }
        }
    }
}
