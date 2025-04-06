package com.lyy.TenYunZhi_001;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            int m = in.nextInt();
            int p = in.nextInt();
            int q = in.nextInt();

            int res = 0;
            while(n > 0){
                int month = 1;
                for(int i = month;i < p;i++){
                    n -= m;
                    res++;
                    if (n <= 0) {
                        System.out.println(res);
                        return;
                    }
                }
                for(int i = p; i < p + q; i++){
                    n -= 2 * m;
                    res++;
                    if (n <= 0) {
                        System.out.println(res);
                        return;
                    }
                }
                for(int i = p + q; i < 13; i++){
                    n -= m;
                    res++;
                    if (n <= 0) {
                        System.out.println(res);
                        return;
                    }
                }
            }


        }
    }
}
