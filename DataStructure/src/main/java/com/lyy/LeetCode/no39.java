package com.lyy.LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class no39 {
    public static void main(String[] args) {

        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        List<List<Integer>> lists = combinationSum(candidates, target);
        lists.forEach(System.out::println);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        findRes(candidates, target, tmp, 0, res);
        return res;
    }

    public static void findRes(int[] candidates, int target, List<Integer> tmp, int idx, List<List<Integer>> res){
        if(idx == candidates.length){
            return;
        }

        if(target == 0){
            res.add(new ArrayList<>(tmp));
            return;
        }

        findRes(candidates, target, tmp, idx + 1, res);
        if(target - candidates[idx] > 0){
            tmp.add(candidates[idx]);
            findRes(candidates, target - candidates[idx], tmp, idx + 1, res);
            tmp.remove(tmp.size() - 1);
        }

    }

}
