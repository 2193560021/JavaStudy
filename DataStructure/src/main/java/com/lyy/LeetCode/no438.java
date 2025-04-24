package com.lyy.LeetCode;

import java.util.*;

public class no438 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        int winLen = p.length();
        int l = 0;
        int r = l + winLen;
        List<Integer> res = new ArrayList<>();
//        while(r <= s.length()){
//            String tmpStr = s.substring(l, r);
//            char[] tmpArr = tmpStr.toCharArray();
//            char[] pArr = p.toCharArray();
//            Arrays.sort(tmpArr);
//            Arrays.sort(pArr);
//            if(new String(tmpArr).equals(new String(pArr))){
//                res.add(l);
//            }
//            l++;
//            r = l + winLen;
//        }

        Set<String> pSet = new HashSet<>();
        for(int i = 0; i < winLen; i++){
            pSet.add(p.substring(i, i + 1));
        }
        while(r <= s.length()){
            boolean flag = true;
            String tmpStr = s.substring(l, r);
            char[] tmpArr = tmpStr.toCharArray();
            for(int i = 0; i < winLen; i++){
                if(!pSet.contains(tmpStr.substring(i, i + 1))){
                    flag = false;
                }
            }
            if(flag){
                res.add(l);
            }
            l++;
            r = l + winLen;
        }




        res.forEach(System.out::println);

    }
}
