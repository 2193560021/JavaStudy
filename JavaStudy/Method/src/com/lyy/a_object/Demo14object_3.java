package com.lyy.a_object;

public class Demo14object_3 {
    public static void main(String[] args) {

        AAA aaa = new BBB();
        test(aaa);


    }

    static void test(AAA aaa){
        System.out.println("aaa");
    }

    static void test(BBB bbb){
        System.out.println("bbb");
    }
}

class AAA{

}

class BBB extends AAA{

}


