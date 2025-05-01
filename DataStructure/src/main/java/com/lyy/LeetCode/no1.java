package com.lyy.LeetCode;

public class no1 {
    public static void main(String[] args) {
        int[] nums = {2,5,5,11};
        int target = 10;

        int len = nums.length;
        int l = 0;
        int r = 1;
        int[] res = new int[2];
        while(l < len && r < len){
            if(nums[l] + nums[r] == target){
                res[0] = l;
                res[1] = r;
                break;
            }else if(nums[l] + nums[r] < target){
                r++;
            }else{
                l++;
            }
        }

        for (int re : res) {
            System.out.println("re = " + re);
        }
    }
}
