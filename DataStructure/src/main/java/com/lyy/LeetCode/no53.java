package com.lyy.LeetCode;

public class no53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        int pre = 0;
        int maxRes = nums[0];
        for(int x : nums){
            pre = Math.max(pre + x, x);
            maxRes = Math.max(maxRes, pre);
        }

        System.out.println("maxRes = " + maxRes);
    }
}
