package com.lyy.a_object;

public class Demo03object {
    public static void main(String[] args) {
        Chinese c = new Chinese();
        c.name = "LX";

        System.out.println("hello" + c.name + c.nation);

        Bird.fly();
        System.out.println(Bird.name);
    }

}

class Bird{

    static String name = "鸟";

    static void fly(){
        System.out.println("飞");
    }
}

class Chinese{
    String name;

    String nation = "China";
}

