package com.lyy;

import java.util.*;
import java.math.*;

public class GuiYiHua {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float[] a = new float[5];
        for (int i = 0 ; i < 5;i++){
            a[i] = scanner.nextFloat();
        }

        float[] b = new float[5];

        float max = a[0];
        float min = a[0];

        for (int i = 1;i < 5;i++){
            if(a[i]>max) max = a[i];
            if(a[i]<min) min = a[i];
        }
        System.out.println(max);
        System.out.println(min);

        for(int i = 0 ; i < 5;i++ ){
            b[i] =(b[i] - min)/(max - min);
            System.out.println(b[i]);
        }

    }
}
