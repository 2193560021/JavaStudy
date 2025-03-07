package com.lyy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SortCollections {
    public static void main(String[] args) throws Exception {
        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        //arr[i] = scanner.nextInt();

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] res = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(res[i]);
        }


        //归并排序
        // MergeSort(arr,0,n - 1);

        //快速排序
        QuickSort(arr,0,n - 1);

        for (int x = 0; x < n; x++) {
            System.out.println(arr[x]);
        }
    }


    //归并排序
    public static void MergeSort(int[] arr, int l, int r){
        if(l >= r) return;
        int mid = (l + r) >> 1;
        MergeSort(arr, l, mid);
        MergeSort(arr, mid + 1, r);

        int[] temp = new int[r - l + 1];
        int k = 0; 
        int i = l;
        int j = mid + 1;
        while(i <= mid && j<= r){
            if(arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while(i <= mid) temp[k++] = arr[i++];
        while(j <= r) temp[k++] = arr[j++];

        for(int m = l,n = 0;m <= r; m++,n++) arr[m] = temp[n];

    }

    //快速排序
    public static void QuickSort(int[] arr, int l, int r){
        if(l >= r) return;
        int flag = arr[(l + r) >> 1];

        int i = l - 1;
        int j = r + 1;
        while(i < j){
            do i++; while(arr[i] < flag);
            do j--; while(arr[j] > flag);
            if(i < j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        QuickSort(arr, l, j);
        QuickSort(arr, j + 1, r);
    }
}


