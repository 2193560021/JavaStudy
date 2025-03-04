package com.lyy.Algorithm;

import java.util.Scanner;

public class DiKgeShu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        QuickSort(arr,0,n - 1);
        System.out.println(arr[k - 1]);
    }

    public static void QuickSort(int[] arr, int l, int r){
        if(l >= r) return;

        int flag = arr[(l + r) >> 1];

        int i = l - 1;
        int j = r + 1;
        while(i < j){
            do i++; while(arr[i] < flag);
            do j--; while(arr[j] > flag);
            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        QuickSort(arr,l,j);
        QuickSort(arr,j + 1,r);
    }
}
