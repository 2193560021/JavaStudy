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
        MergeSort(arr,0,n - 1);

        //快速排序
        QuickSort(arr,0,n - 1);

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void MergeSort(int[] arr, int l, int r){
        if(l >= r) return;
    }


    public static void QuickSort(int[] arr, int l, int r){

    }
}
