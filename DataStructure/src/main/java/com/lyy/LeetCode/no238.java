package com.lyy.LeetCode;

import java.util.Arrays;

public class no238 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,1,8,2};
        int[] ints = productExceptSelf(nums);

        for (int i : ints) {
            System.out.println("i = " + i);
        }
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,1);
        int beforeSum = 1;
        int afterSum = 1;
        for(int i = 0,j = n - 1;i < n; i++, j--){
            ans[i] *= beforeSum;
            ans[j] *= afterSum;
            beforeSum *= nums[i];
            afterSum *= nums[j];
        }
        return ans;
    }
}
