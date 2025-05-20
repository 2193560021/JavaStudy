package com.lyy.LeetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class no394 {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Deque<Integer> stack_multi = new LinkedList<>();
        Deque<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()){
            if(c == '['){
                stack_multi.add(multi);
                stack_res.add(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']'){
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();

    }
}
