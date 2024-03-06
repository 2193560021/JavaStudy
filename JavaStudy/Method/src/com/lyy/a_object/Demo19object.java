package com.lyy.a_object;

public class Demo19object {
    public static void main(String[] args) {
        /*
        JVM默认给类提供的构造方法，其实就是公共的，无参的构造方法
        1.类的创建过程复杂
        2.类的对象消耗资源
        */

//        User19 user19 = new User19();
        User19 instance = User19.getInstance();
        User19 instance1 = User19.getInstance();
        User19 instance2 = User19.getInstance();
        User19 instance3 = User19.getInstance();
        User19 instance4 = User19.getInstance();
        User19 instance5 = User19.getInstance();

        System.out.println(instance2.equals(instance5));

    }
}

class User19{
    private static User19 user19 = null;

    private User19(){

    }
    public static User19 getInstance(){
        if(user19 == null){
            user19 = new User19();
        }
        return user19;
    }
}