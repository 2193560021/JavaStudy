package com.lyy;

public class Java2019C02 {
    public static void main(String[] args) {
        int num = 0;
        for(int i = 0; i < 10000;i++){
            String Si = i + "";
            String Sj = i*i*i + "";
            if(Sj.endsWith(Si)) num++;
        }
        System.out.println(num);
    }
}
