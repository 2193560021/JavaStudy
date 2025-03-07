package com.lyy.Algorithm;

import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
//        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        MergeSort(arr,0,n - 1);
//        System.out.println(arr[k - 1]);

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void MergeSort(int[] arr, int l, int r){
        if(l >= r) return;

        int mid = (l + r) >> 1;
        int[] temp = new int[r - l + 1];
        MergeSort(arr,l,mid);
        MergeSort(arr,mid + 1,r);
        int i = l;
        int j = mid + 1;
        int k = 0;
        while(i <= mid && j <= r) {
            if(arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while(i <= mid) temp[k++] = arr[i++];
        while(j <= r) temp[k++] = arr[j++];
        for (int m = l,n = 0; m <= r; m++,n++) {
            arr[m] = temp[n];
        }

    }
}
