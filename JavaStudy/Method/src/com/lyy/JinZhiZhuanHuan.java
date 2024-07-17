package com.lyy;

import java.math.BigInteger;
import java.util.Scanner;

public class JinZhiZhuanHuan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        BigInteger x = input.nextBigInteger(m);
        String s = x.toString(n);
        System.out.println(s);
        input.close();
    }
}
