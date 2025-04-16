package com.lyy.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class no22 {
    public static void main(String[] args) {
        int n = 3;
        List<String> list = generateParenthesis(n);
        list.forEach(System.out::println);

    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();


        getAll(new char[2 * n], 0, res );

        return res;


    }

    static void getAll(char[] tmp, int pos, List<String> res){
        if(pos == tmp.length){
            if(check(tmp)){
                res.add(new String(tmp));
            }
        }else {
            tmp[pos] = '(';
            getAll(tmp, pos + 1, res);
            tmp[pos] = ')';
            getAll(tmp, pos + 1, res);

        }
    }

    static boolean check(char[] tmp){
        int res = 0;
        for (char c : tmp) {
            if(c == '(') res++;
            else res--;
            if(res < 0) return false;
        }
        return res == 0;
    }
}
