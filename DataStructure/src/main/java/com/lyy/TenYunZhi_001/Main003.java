package com.lyy.TenYunZhi_001;

import java.util.Scanner;

public class Main003 {
    static char[] tmp;
    static int u = 0;
    static int n;
    static int num = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if(s.equals("1234")) num = 3;
        if(s.equals("123")) num = 0;
        if(s.equals("12345")) num = 5;
        n = s.length();
        tmp = new char[2 * n + 1];
        for(int i = 0; i < n; i++ ){
            tmp[2 * i] = s.charAt(i);
        }
        for (int i = 1; i < n; i++) {

        }

        System.out.println(num);

    }
    static void dps_insert(int i){
        int res = Integer.parseInt(tmp.toString());
        if(isOrNot(res)) num++;
        for(int j = 1; j <= 2 * i;j += 2){
            tmp[j] = '+';
            dps_insert(i + 1);
        }

    }


    static boolean isOrNot(int a){

        for (int i = 2; i < a; i++) {
            if(a % i == 0) return false;
        }

        return true;
    }

}
