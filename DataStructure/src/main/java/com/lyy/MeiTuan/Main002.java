package com.lyy.MeiTuan;

import java.util.*;

public class Main002 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int bl = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            bl += arr[i];

        }
        int[] b = new int[bl];
        int flag = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= arr[i]; j++){
                b[flag++] = j;
            }
        }

        int sum = 0;
        int l = 0;
        int maxW = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int r = 0; r < bl; r++){
            int num = b[r];
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxW = Math.max(maxW, num);

            while (map.get(num) > 1 || maxW > (r - l + 1)){
                int ln = b[l];
                map.put(ln, map.get(ln) - 1);
                if(map.get(ln) == 0){
                    map.remove(ln);
                }
                l++;
                maxW = 0;

                for (int key : map.keySet()){
                    maxW = Math.max(maxW, key);
                }
            }
            if(maxW == (r - l + 1) && map.size() == maxW){
                sum++;
            }
        }

        System.out.println(sum);

    }
}
