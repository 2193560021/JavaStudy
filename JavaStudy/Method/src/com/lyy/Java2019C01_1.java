package com.lyy;

import java.security.Key;

public class Java2019C01_1 {
    public static void main(String[] args) {
        int sum = 0;
        int geshu = 0;
        for (int i = 0;i <= 2019;i++){
            String j = i + "";
            char[] num =  j.toCharArray();
            for(int k = 0; k < num.length; k++){
                if(num[k] == '0' || num[k] == '1' ||num[k] == '2' ||num[k] == '9'){
                    System.out.println(i);
                    sum += i;
                    break;
                }
            }
        }
        System.out.println(sum);
//        System.out.println(geshu);
    }
}
