package com.lyy.LeetCode;

public class No283 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        int r = nums.length - 1;
        while(r == 0 ) r--;
        int l = 0;
        System.out.println(l);
        System.out.println(r);
        while(l <= r){
            if(nums[l] == 0){
                int tmp = nums[l];
                for(int i = l;i < r; i++){
                    nums[l] = nums[l + 1];
                }
                nums[r] = 0;
                r--;
                System.out.println(l);
            }else l++;
        }

    }
}
