package com.lyy.a_object;

public class Demo14object_1 {
    public static void main(String[] args) {

        User141 u1 = new User141();
//        User141 u2 = new User141("LYY");
//        User141 u3 = new User141("LX","女");


    }
}

class User141{
    User141(){
        this("LLL");
    }
    User141(String name){
        this(name,"男");
    }

    User141(String name,String gender){
        System.out.println(name + gender);
    }
}
