package com.lyy.a_object;

public class Demo08object {
    public static void main(String[] args) {

//        User11 u = new User11("LX");

//        u.test();
        Child c = new Child("LYY");

        c.test();
    }
}

class Child extends User22{

    Child(String name) {
        super(name);
    }
}

class User22{


    User22(String name) {
        System.out.println("user..." + name);
    }

    void test(){
        System.out.println("test...");
    }

}
