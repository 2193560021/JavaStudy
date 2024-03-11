package com.lyy.a_object;

import java.sql.SQLOutput;

public class Demo23object {
    public static void main(String[] args) {

        //抽象类: 不完整的类，就是抽象类
//        Person21 person21 = new Person21();
        System.out.println(City.BEIJING.name);
        System.out.println(City.SHANGHAI.code);


    }
}

enum City{
    BEIJING("北京",1001), SHANGHAI("上海",1002);
    City(String name, int code){
        this.code = code;
        this.name = name;
    }

    public String name;
    public int code;
}