package com.lyy.a_object;

public class Demo20object {
    public static void main(String[] args) {

        final String name = "LXX;";

        System.out.println();

        User20 u = new User20("LYY_YYY");
//        u.name = "LYY";
        System.out.println(u.name);
//        u.name = "LX";
        System.out.println(u.name);
    }
}

class User20{
    public final String name ;
    User20(String name){
        this.name = name;
    }

    public final void test(){

    }
}
