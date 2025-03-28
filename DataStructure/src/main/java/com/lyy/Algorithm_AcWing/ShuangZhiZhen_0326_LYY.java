package com.lyy.Algorithm_AcWing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShuangZhiZhen_0326_LYY {

    /**
     * 刚开始两个指针i和j都指向0，i负责从头开始往后遍历，j先留在原地，
     * 先看看有没有不重复的元素，
     * 如果没有重复元素，则s[a[i]]++后值为1，
     * 如果有重复元素（此时res保存的是目前没有重复的范围），则s[a[i]]++值变成2，并且说明刚刚加入的这个a[i]元素是重复元素，
     * 这个时候j就派上用场了，用它来从0开始往后找可以让数组里面有不重复元素的那个位置，通过s[a[j]]--来把前面那些和s[a[i]]
     * 不一样的数先排除掉（并且j一直往后移动），当排除到和s[a[i]]值一样的那个数时，由于s[a[j]]--，
     * 所以s[a[i]]的值从2减为了1，此时j还会后移一位，可以保证现在新的区间里面没有和s[a[i]]值一样的那个数了，
     * 也就是没有重复的数了，以此类推。
     * 全程都在记录res的最大值，直到i最后遍历到数组尾部，j到i之间也没有重复值而停止。
     */

    static int n;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String[] No = br.readLine().split(" ");
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(No[i]);
        }

        int res_max = 0;

        int[] s = new int[100010];

        for (int i = 0, j = 0; i < n; i++) {
            s[a[i]] ++;
            while(s[a[i]] > 1){
                s[a[j]] --;
                j++;
            }
            if(i - j + 1 >= res_max) res_max = i - j + 1;
        }
        System.out.println(res_max);

    }

}
