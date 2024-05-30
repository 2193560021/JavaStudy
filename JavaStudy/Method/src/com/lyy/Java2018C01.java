package com.lyy;

public class Java2018C01 {
    public static void main(String[] args) {
        int m = 0;
        int e = 1;
        int d = 0;
        while(m < 108){
            m += e;
            System.out.println("m-" + m);
            e += 2;
            System.out.println("e-" + e);
            d++;
            System.out.println("d-" + d);
        }
        System.out.println(d);
    }
}
