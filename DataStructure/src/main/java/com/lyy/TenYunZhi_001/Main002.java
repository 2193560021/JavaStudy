package com.lyy.TenYunZhi_001;

import com.lyy.Algorithm_AcWing.QuickSort;

import java.util.Scanner;

public class Main002 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            QuickSort(a, 0, n - 1);
            int flag = 1;

            for (int i = 1; i < n; i++) {
                if((a[i] - a[i - 1]) > 1){
                    flag = 0;
                }
            }

            if (flag == 1) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static void QuickSort(int[] a, int l, int r){
        if(l >= r) return;

        int flag = a[(l + r) >> 1];

        int i = l - 1;
        int j = r + 1;

        while(i < j){
            do i++; while (a[i] < flag);
            do j--; while (a[j] > flag);
            if(i < j){
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        QuickSort(a, l, j);
        QuickSort(a, j + 1, r);

    }
}
