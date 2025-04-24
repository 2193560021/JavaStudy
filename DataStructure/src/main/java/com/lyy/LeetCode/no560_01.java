package com.lyy.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class no560_01 {
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
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        System.out.println("res = " + res);
    }
}
