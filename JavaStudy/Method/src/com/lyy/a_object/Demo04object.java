package com.lyy.a_object;

public class Demo04object {
    public static void main(String[] args) {
        Test t = new Test();
        t.test();
        t.test1();

        Test.test1();
    }

}

class Test{
    String name;
    static String sex;

    void test(){
        test1();
    }

    static void test1(){

    }
}

class Bird1{

    static String name = "鸟";

    static void fly(){
        System.out.println("飞");
    }
}

