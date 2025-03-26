package com.lyy.Algorithm_AcWing;

import java.io.*;

public class ChaFen_0325 {
    static int n = 0;
    static int m = 0;
    static int[] arr;
    static int[] tmp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] No1 = br.readLine().split(" ");

        n = Integer.parseInt(No1[0]);
        m = Integer.parseInt(No1[1]);
        arr = new int[n + 2];
        tmp = new int[n + 2];
        String[] No2 = br.readLine().split(" ");
        for (int i = 1; i <= n; i ++) {
            arr[i] = Integer.parseInt(No2[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            insert(i, i, arr[i]);
        }

        while(m -- > 0){
            String[] No = br.readLine().split(" ");
            int l = Integer.parseInt(No[0]);
            int r = Integer.parseInt(No[1]);
            int c = Integer.parseInt(No[2]);
            insert(l, r, c);
        }
        for (int i = 1; i <= n; i++) {
            tmp[i] += tmp[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(tmp[i] + " ");
        }
    }

    public static void insert(int l, int r, int c){
        tmp[l] += c;
        tmp[r + 1] -= c;
    }

}
