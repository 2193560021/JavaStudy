package com.lyy.a_method;

public class Demo1Method {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        method(a,b);

        System.out.println(sum(10, 20));
    }

    public static void method(int a,int b){
        System.out.println(a+"..."+b);
    }

    public static int sum(int a,int b){
        return a + b;
    }
}
