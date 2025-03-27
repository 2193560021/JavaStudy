package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShuangZhiZhen_0326_LYY {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String[] No = br.readLine().split(" ");
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(No[i]);
        }

        Get(arr, 0, n - 1);

    }

    static int Get(int[] arr, int l, int r){
        int mid = l + r >> 1;
        int i = l;
        int j = r;
        for (int m = l; m < r; m++) {
            int[] tmp = new int[r - l + 1];
        }

        int res = 0;

        return res;
    }
}
