package com.lyy.LeetCode;

public class No125 {
    public static void main(String[] args) {
        int a1 = 'A';
        int z1 = 'Z';
        int a2 = 'a';
        int z2 = 'z';
        System.out.println("A = " + a1);
        System.out.println("Z = " + z1);
        System.out.println("a = " + a2);
        System.out.println("z = " + z2);
        System.out.println(a2-a1);

        String s = "3P";

        int n = 1;
        int[] a = new int[s.length()];
        char[] tmp = s.toCharArray();
        int m = 0;
        for(int i = 0; i < tmp.length; i++){
            if(tmp[i] >= 65 && tmp[i] <= 90){
                a[m++] = tmp[i] + 32;
            }else if((tmp[i] >= 97 && tmp[i] <= 122) || (tmp[i] >= 48 && tmp[i] <= 57)){
                a[m++] = tmp[i];
            }
        }
        System.out.println("m = " + m);

        char[] res = new char[m];
        for (int i = 0; i < m; i++) {
            res[i] = (char) a[i];
            System.out.println("res[i] = " + res[i]);
        }

        int flag = 1;

        for(int i = 0,j = m - 1;i < m / 2;i++,j--){
            if(a[i] != a[j]) flag = 0;
        }

        System.out.println("flag = " + flag);


    }
}
