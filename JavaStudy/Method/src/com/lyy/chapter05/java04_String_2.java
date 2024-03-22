package com.lyy.chapter05;

public class java04_String_2 {
    public static void main(String[] args) {

        String s = "";
        for (int i = 0;i < 100;i++){
            s += i;
        }
        System.out.println(s);

        StringBuilder s1 = new StringBuilder();
        for (int i = 0;i < 100;i++){
            s1.append(i);
        }
        System.out.println(s1);
        System.out.println(s1.reverse());
    }
}