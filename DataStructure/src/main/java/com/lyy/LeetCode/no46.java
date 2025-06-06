package com.lyy.LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class no46 {
    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3};

        List<List<Integer>> permute = permute(nums);

        permute.forEach(System.out::println);
    }

    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len == 0) return res;

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);

        dfs(nums, len, 0, path, used, res);

        return res;
    }

    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res){

        if(depth == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++){
            if(!used[i]){
                path.addLast(nums[i]);
                used[i] = true;

                System.out.println("递归前：=>" + path);
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.removeLast();
                System.out.println("递归后：=>" + path);
            }
        }

    }
}
