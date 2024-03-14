package com.lyy.chapter05;

public class java01_Object {
    public static void main(String[] args) {
        Object obj = new User();

        String s = obj.toString();
        System.out.println(s);
    }
}

class Person{

}

class User extends Person{

}