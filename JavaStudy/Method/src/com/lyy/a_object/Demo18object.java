package com.lyy.a_object;

public class Demo18object {
    public static void main(String[] args) {
        /*

        */

        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();



    }
}

class OuterClass{
    public class InnerClass{

    }
}