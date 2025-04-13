package com.lyy.Algorithm_AcWing;

import java.util.Scanner;

public class no905_TanXin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        QSort(arr, 0, n - 1);

        int num = 1;
        int flag = arr[0][1];
        for (int i = 0; i < n; i++) {
            if(!(arr[i][0] <= flag && flag <= arr[i][1])){
                flag = arr[i][1];
                num++;
            }
        }

        System.out.println(num);


    }

    private static void QSort(int[][] arr, int l, int r){
        if(l >= r) return;
        int i = l - 1;
        int j = r + 1;
        int flag = arr[(l + r) >> 1][1];

        while(i < j){
            do i++; while (arr[i][1] < flag);
            do j--; while (arr[j][1] > flag);
            if(i < j){
                int tmp0 = arr[i][0];
                int tmp1 = arr[i][1];
                arr[i][0] = arr[j][0];
                arr[i][1] = arr[j][1];
                arr[j][0] = tmp0;
                arr[j][1] = tmp1;
            }
        }

        QSort(arr, l, j);
        QSort(arr, j + 1, r);



    }
}
