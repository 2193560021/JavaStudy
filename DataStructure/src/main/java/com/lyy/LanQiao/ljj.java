package com.lyy.LanQiao;

import java.math.BigInteger;
import java.util.Scanner;

public class ljj {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int l = 0;
        int l1 = 0;
        long res = 0;
        for (int i = 1; i <= n; i++) {
            String b = Integer.toBinaryString(i);
            System.out.println("b = " + b);
            l += b.length();
            for (int j = 0; j < b.length(); j++){
                if(b.charAt(j) == '1') l1++;
            }

        }

        for(int i = l1; i > 0; i--){
            res += Math.pow(2, i);
        }

        System.out.println("res = " + res);


    }
}
