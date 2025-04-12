package com.lyy.Test0411;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{15,27,13,24,11,28,16};

        MSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.println("i = " + i);
        }
    }

    private static void MSort(int[] arr, int l, int r){
        if(l >= r) return;
        int mid = (l + r) >> 1;
        int i = l;
        int j = mid + 1;

        MSort(arr, l, mid);
        MSort(arr, mid + 1, r);

        int[] tmp = new int[r - l + 1];

        int k = 0;
        while(i <= mid && j <= r){
            if(arr[i] <= arr[j]) tmp[k++] = arr[i++];
            else tmp[k++] = arr[j++];
        }
        while(i <= mid) tmp[k++] = arr[i++];
        while(j <= r) tmp[k++] = arr[j++];

        for (int p = l,q = 0; p <= r;p++,q++)
            arr[p] = tmp[q];

    }
}
