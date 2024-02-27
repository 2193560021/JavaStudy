package com.lyy.a_method;

public class Demo2Method {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        bl(a);
    }

    public static void bl(int[] a){
        for (int i = 0;i < a.length ;i++){
            System.out.println(a[i]);
        }
    }
}
