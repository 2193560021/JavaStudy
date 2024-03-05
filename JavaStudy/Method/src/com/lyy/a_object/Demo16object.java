package com.lyy.a_object;

public class Demo16object {
    public static void main(String[] args) {

//        CCC c = new CCC();
//        System.out.println(c.sum());

        int result = computeAP(100);
        System.out.println(result);
    }

    public static int computeAP(int num) {
        if(num == 1){
            return 1;
        }else {
            return num + computeAP(num - 1);
        }
    }
}

