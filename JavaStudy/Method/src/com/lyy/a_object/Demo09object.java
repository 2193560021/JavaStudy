package com.lyy.a_object;

public class Demo09object {
    public static void main(String[] args) {

//        User11 u = new User11("LX");

//        u.test();
        Child1 c = new Child1();

        c.test();
    }
}
class Parent1{

    String name = "LX";

}

class Child1 extends Parent1{

    String name = "lisi";
    void  test(){
        System.out.println(super.name);
        System.out.println(this.name);
    }
}


