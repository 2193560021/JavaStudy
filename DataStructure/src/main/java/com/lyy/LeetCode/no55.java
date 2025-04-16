package com.lyy.LeetCode;

public class no55 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,2,3};
        int b = canJump(nums);
        System.out.println("b = " + b);


    }

    public static int canJump(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxPos = 0;
        int steps = 0;
        for(int i = 0; i < len - 1; i++){
            maxPos = Math.max(maxPos, i + nums[i]);
            if(i == end){
                end = maxPos;
                steps++;
            }
        }

        return steps;



    }
}
