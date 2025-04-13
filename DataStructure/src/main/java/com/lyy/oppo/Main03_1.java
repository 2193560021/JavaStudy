package com.lyy.oppo;

import java.util.Arrays;
import java.util.Scanner;

public class Main03_1 {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int ai = 0;
        int bi = 0;
        long num = 0;
        for(int aj = 0; aj < n; aj++){
            for(int bj = 0; bj < n; bj++){
                if(a[aj] <= b[bi]){
                    num++;
                }
            }
        }


        System.out.println(num == 0 ? -1 : num % MOD);
    }
}
