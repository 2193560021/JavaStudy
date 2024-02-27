package com.lyy.a_object;

public class Demo05object {
    public static void main(String[] args) {
        User5.test();
    }
  
}

class User5{
    static {
        System.out.println("静态代码块执行");
    }

    static void test(){
        System.out.println("test...");
    }
}
