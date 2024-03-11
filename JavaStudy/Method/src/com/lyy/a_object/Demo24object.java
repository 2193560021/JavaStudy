package com.lyy.a_object;

public class Demo24object {
    public static void main(String[] args) {

        //抽象类: 不完整的类，就是抽象类
//        Person21 person21 = new Person21();
        Me me = new Me();
        me.sayHello(new ZhangSan());
    }
}

abstract class Person24{
    public abstract String name();
}

class Me{
    public void sayHello(Person24 person24){
        System.out.println("Hello" + person24.name());
    }
}

class ZhangSan extends Person24{
    public String name(){
        return "ZhangSan";
    }
}

class Lisi extends Person24{
    public String name(){
        return "Lisi";
    }
}