package com.lyy.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class no78 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for(Integer n : nums){
            int size = res.size();
            for (int i = 0; i < size; i++){
                List<Integer> tmp = new ArrayList<>(res.get(i));
                tmp.add(n);
                res.add(tmp);
            }
        }
        res.forEach(System.out::println);
    }
}
