package com.lyy.a_object;

import com.lyy.a_method.Demo3Method;

public class Demo07object {
    public static void main(String[] args) {

        User11 u = new User11("LX");

//        u.test();
    }
}

class User11{

    static {
        System.out.println("11");
    }

    {
        System.out.println("1");
    }

    static {
        System.out.println("22");
    }

    User11(String name) {
        System.out.println("user..." + name);
    }

    static {
        System.out.println("33");
    }
    {
        System.out.println("2");
    }

    static {
        System.out.println("44");
    }
    void test(){
        System.out.println("test...");
    }

    static {
        System.out.println("55");
    }
    {
        System.out.println("3");
    }

    static {
        System.out.println("66");
    }
}
