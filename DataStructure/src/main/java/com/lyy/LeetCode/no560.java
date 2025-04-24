package com.lyy.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class no560 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,1,1,2,3};
        int k = 3;

        int res = 0;
        int len = nums.length;
        int l = 0;
        int r = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        int sum = 0;
        while(r < len){

            sum += nums[r];
            r++;
            while(sum > k && l < r){
                sum -= nums[l];
                l++;
            }
            if(sum == k){
                res++;
//                if(r == len) break;
            }
        }

        System.out.println("res = " + res);
    }
}
