package com.lyy.oppo;
import java.util.Scanner;
import java.math.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class test01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int index = 0;
        String s = " ";
        // 注意 hasNext 和 hasNextLine 的区别
        double h = Math.pow(2, n);
        for(int i = 1; i <= h ; i++){
            String no = in.next();
            s += no;
        }
        s += " ";
        double times = h * h;
        for(int i = 1; i <=  times; i++){
            while(i % 4 == 0){
                String nn = s.substring(i - 3, i + 1);
                int san = nn.charAt(0) == '.' ? 0 : 1;
                int er = nn.charAt(1) == '.' ? 0 : 1;
                int yi = nn.charAt(2) == '.' ? 0 : 1;
                int ling = nn.charAt(3) == '.' ? 0 : 1;
                int res = 0;
                if(san == 1) res += Math.pow(2, 3);
                if(er == 1) res += Math.pow(2, 2);
                if(yi == 1) res += Math.pow(2, 1);
                if(ling == 1) res += Math.pow(2, 0);

                System.out.print(res + " ");
                break;
            }

        }
    }
}