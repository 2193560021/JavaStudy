package com.lyy.ZuoYeBang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main002 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String no1 = in.nextLine();
        String[] no = no1.split(",");
        int len = no.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = Integer.parseInt(no[i]);
        }

        Arrays.sort(nums);

        int res1 = nums[len - 1] * nums[len - 2] * nums[len - 3];
        int res2 = nums[0] * nums[1] * nums[len - 1];

        System.out.println(Math.max(res1, res2));


    }
}
