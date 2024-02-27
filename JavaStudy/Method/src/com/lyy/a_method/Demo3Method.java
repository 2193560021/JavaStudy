package com.lyy.a_method;

public class Demo3Method {
    public static void main() {

        int[] b = bl();
        for (int i = 0;i < b.length ;i++){
            System.out.println(b[i]);
        }
    }

    public static int[] bl(){
        int[] ab = {1,2,3,4,5,6,7};

        return ab;
    }
}
