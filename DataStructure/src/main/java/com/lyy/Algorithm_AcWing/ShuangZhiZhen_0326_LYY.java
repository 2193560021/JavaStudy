package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShuangZhiZhen_0326_LYY {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] No = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(No[i]);
        }
    }
}
