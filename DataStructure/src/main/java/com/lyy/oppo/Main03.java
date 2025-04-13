package com.lyy.oppo;

import java.util.Arrays;
import java.util.Scanner;

public class Main03 {

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

        long[] pow2 = new long[n];

        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        long[] cntA = new long[n];
        for (int i = 1; i < n; i++) {
            cntA[i] = pow2[i];
        }

        long[] cntB = new long[n];
        for (int i = 1; i < n; i++) {
            cntB[i] = pow2[n - i - 1];
        }

        long[] preA = new long[n];
        preA[0] = cntA[0];
        for (int i = 1; i < n; i++) {
            preA[i] = (preA[i - 1] + cntA[i]) % MOD;
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            int t = b[i];
            int low = 0, high = n - 1;
            int nowM = -1;
            while(low <= high){
                int mid = (low + high) >> 1;
                if(a[i] <= t){
                    nowM = mid;
                    low = mid + 1;
                }else high = mid - 1;
            }
            if(nowM != -1)
                res = (res + preA[nowM] * cntB[i]) % MOD;
        }

        System.out.println(res == 0 ? -1 : res % MOD);
    }
}
