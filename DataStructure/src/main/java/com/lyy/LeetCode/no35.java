package com.lyy.LeetCode;

public class no35 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 5;
        System.out.println(searchInsert(nums, target));


    }

    public static int searchInsert(int[] nums, int target) {
        return find(nums, 0, nums.length, target);
    }

    public static int find(int[] nums, int l, int r, int target){
        int flag = 0;
        if(l >= r ) return r;
        int mid = (l + r) >> 1;

        if(target < nums[mid]) flag = find(nums, l, mid, target);
        else if(target > nums[mid]) flag = find(nums, mid + 1, r, target);
        else flag = mid;
        return flag;

    }
}
