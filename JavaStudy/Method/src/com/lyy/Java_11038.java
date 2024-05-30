package com.lyy;

import java.util.Scanner;

public class Java_11038 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int num = 0;
        int[] a = new int[106];
        for(int i = 1; i <= 105; i++){
            for(int j = i + 1;j <= 105; j++){
                System.out.println("i+j::" + (i + j));
                int flag = 0;
                for(int k = 2;k <= 105;k++){
                    if((i+j) % k == 0 && 105 % k == 0){
                        flag = -1;
                        break;
                    }else{
                        flag = 1;
//                        a[i] = 1;
                    }
                }
                if(flag == -1){
                    a[i] = -1;
                } else if (flag == 1) {
                    a[i] = 1;
                }
            }
        }
        for(int i = 1;i <=105;i++){
            System.out.println(a[i]);
            if(a[i] == 1) num++;
        }
        System.out.println(num);

        scan.close();
    }
}
