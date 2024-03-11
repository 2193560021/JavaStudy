package com.lyy.a_object;

public class Demo21object {
    public static void main(String[] args) {

        //抽象类: 不完整的类，就是抽象类
        //  因为类不完整，无法构建对象
        // 抽象类无法直接构建对象，但可以通过子类简介构建对象
//        Person21 person21 = new Person21();

        Chinese21 chinese21 = new Chinese21();
        chinese21.eat();
    }
}

abstract class Person21{
    public abstract void eat();
    public void test(){

    }
}

class Chinese21 extends Person21{
    public final void eat(){
        System.out.println("中国人用筷子吃饭");
    }
}