package com.lyy.a_object;

public class Demo10object {
    public static void main(String[] args) {

//        User11 u = new User11("LX");

//        u.test();
        Child2 c1 = new Child2();
        Child2 c2 = new Child2();
        Child2 c3 = new Child2();
        System.out.println(c1.username);

    }
}
class Parent2{
    String username;

    Parent2(String name){
        username = name;
        System.out.println("parent...");
    }

}

class Child2 extends Parent2{

    Child2(){
        super("LYY");
        System.out.println("child...");
    }
}


