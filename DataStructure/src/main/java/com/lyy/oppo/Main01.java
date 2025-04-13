package com.lyy.oppo;

import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int x = in.nextInt();
        int k = in.nextInt();

        int yu = x - k;

        int nowMax = Math.max(Math.max(a, b), c);
        if((yu + 1) >= nowMax) System.out.println("NO");
        else System.out.println("YES");


    }
}
