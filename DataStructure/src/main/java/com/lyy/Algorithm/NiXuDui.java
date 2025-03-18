package com.lyy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NiXuDui {

    private static Long rs = 0L;

    public static void main(String[] args) throws Exception {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bu = new BufferedReader(in);

        int n = Integer.parseInt(bu.readLine());
        int[] arr = new int[n];

        String[] res = bu.readLine().split(" ");
        for(int i = 0; i< n; i++){
            arr[i] = Integer.parseInt(res[i]);
        }

        Merge_Result(arr, 0, n - 1);

        System.out.println(rs);

    }


    public static void Merge_Result(int[] arr, int l, int r){
        if (l >= r) return;
        int mid = l + r >> 1;

        Merge_Result(arr, l, mid);
        Merge_Result(arr, mid + 1, r);
        int i = l;
        int j = mid + 1;
        int[] tmp = new int[r - l + 1];
        int k = 0;

        while(i <= mid && j <= r){
            if(arr[i] <= arr[j]){
                tmp[k++] = arr[i++];
            }
            else{
                tmp[k++] = arr[j++];
                rs = rs + (mid - i + 1);
            }
        }
        while(i <= mid) tmp[k++] = arr[i++];
        while(j <= r) tmp[k++] = arr[j++];

        for (int m = l,n = 0; m <= r;m++,n++ ){
            arr[m] = tmp[n];
        }
    }
}
