package com.lyy.a_object;

public class Demo15object {
    public static void main(String[] args) {

        Child15 c15 = new Child15();
        c15.test();

    }
}

class Parent15{
    String name = "LXX";

    void test(){
        System.out.println("parent test...");
    }
}
class Child15 extends Parent15{
    String name = "LYY";

    void test(){
//        System.out.println(this.name);
//        System.out.println(super.name);
        super.test();
        System.out.println("child test...");
    }
}