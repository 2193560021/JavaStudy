package com.lyy.oppo;

import java.util.Arrays;
import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);
        int maxL = -1;

        int i = 0, j = 0;
        while(i < n && j < n){
            if(a[i] <= b[j]){
                int nowL = i - j;
                maxL = nowL > maxL ? nowL : maxL;
                i++;
            }else j++;
        }
        if(maxL == -1) System.out.println(-1);
        else System.out.println(maxL + n + 1);



    }
}
