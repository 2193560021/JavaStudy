package com.lyy.ZuoYeBang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main003 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String no1 = br.readLine();
        int n = Integer.parseInt(no1.split(" ")[0]);
        int q = Integer.parseInt(no1.split(" ")[1]);
        String no2 = br.readLine();
        String[] str = no2.split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        int len = arr.length;
        int l = 0;
        int r = len - 1;


        Set<Integer> yes = new HashSet<>();

        for (int i = 0; i < len; i++) {
            yes.add(arr[i]);
        }

        int[] res = new int[q];
        int flag = 0;

        for (int i = 0; i < q; i++) {
            String non = br.readLine();
            Set<Integer> tmp = new HashSet<>();
            tmp.clear();
            String[] no_str = non.split(" ");
            String direction = no_str[0];
            int cutLong = Integer.parseInt(no_str[1]);
            if(direction.equals("L")){
                if(cutLong > len){
                    cutLong = cutLong % len;
                    tmp.addAll(yes);
                }
                for (int m = 0; m < cutLong; m++) {
                    if(l == len){
                        l = 0;
                    }
                    tmp.add(arr[l]);
                    l++;
                }
                System.out.println(tmp.size());
            } else if (direction.equals("R")) {
                if(cutLong > len){
                    cutLong = cutLong % len;
                    tmp.addAll(yes);
                }
                for (int m = 0; m < cutLong; m++) {
                    if(r == -1){
                        r = len - 1;
                    }
                    tmp.add(arr[r]);
                    r--;
                }
                System.out.println(tmp.size());
            }
        }

        for (int re : res) {
            System.out.println(re);
        }
    }
}
