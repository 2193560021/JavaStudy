package com.lyy.Test0411;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QuickSort {
    public static void main(String[] args) throws Exception {
//        int n = 7;
        int[] arr = new int[]{5,7,3,4,1,8,6};

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String no1 = br.readLine();
//        int n = Integer.parseInt(no1);
//        String[] no2 = br.readLine().split(" ");
//        for (int i = 0; i < n; i++) {
//            arr[i] = Integer.parseInt(no2[i]);
//        }

        QSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.println("i = " + i);
        }
    }

    public static void QSort(int[] arr, int l, int r){
        if(l >= r) return;
        int i = l - 1;
        int j = r + 1;
        int flag = arr[(l + r) >> 1];
        while(i < j){
            do i++; while (arr[i] < flag);
            do j--; while (arr[j] > flag);
            if(i < j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        QSort(arr, l, j);
        QSort(arr, j + 1, r);
    }
}
