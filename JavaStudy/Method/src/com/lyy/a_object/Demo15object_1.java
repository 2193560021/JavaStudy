package com.lyy.a_object;

public class Demo15object_1 {
    public static void main(String[] args) {

//        CCC c = new CCC();
//        System.out.println(c.sum());

        CCC d = new DDD();
        System.out.println(d.sum());
    }
}

class CCC{
    int i  = 10;

    int sum(){
        return getI() + 10;
    }

    int getI(){
        return i;
    }
}

class DDD extends CCC{
    int i  = 20;

//    int sum(){
//        return i + 20;
//    }

    int getI(){
        return i;
    }
}
