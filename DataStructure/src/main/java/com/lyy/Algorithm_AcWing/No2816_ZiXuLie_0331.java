package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No2816_ZiXuLie_0331 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] no1 = br.readLine().split(" ");
        int n = Integer.parseInt(no1[0]);
        int m = Integer.parseInt(no1[1]);
        int[] a = new int[n];
        int[] b = new int[m];
        String[] no2 = br.readLine().split(" ");
        String[] no3 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(no2[i]);
        }
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(no3[i]);
        }
        int i = 0, j = 0;
        while(i < n && j < m){
            if(a[i] == b[j]) i++;
            j++;
        }
        if(i == n) System.out.println("Yes");
        else System.out.println("No");

    }
}
