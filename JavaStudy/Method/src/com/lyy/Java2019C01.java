package com.lyy;

public class Java2019C01 {
    public static void main(String[] args) {
        int sum = 22;
        int geshu = 0;
        for (int i = 11;i <= 2019;i++){

            if(i % 10 == 0 || i % 10 == 1 ||i % 10 == 2 ||i % 10 == 9 ||
                    i / 10 % 10 == 0 || i / 10 % 10 == 1 || i / 10 % 10 == 2 || i / 10 % 10 == 9 ||
                        i / 100 % 10 == 0 || i / 100 % 10 == 1 || i / 100 % 10 == 2 || i / 100 % 10 == 9){
                sum += i;
                System.out.println(i);
                geshu++;
            }
        }
        System.out.println(sum);
        System.out.println(geshu);
    }
}
