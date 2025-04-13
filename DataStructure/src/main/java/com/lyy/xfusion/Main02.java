package com.lyy.xfusion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main02 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        String C = in.nextLine();
        String[] CArray = C.split(" ");
        int CNum = CArray.length;
        int[] fruitCount = new int[CNum];
        for (int i = 0; i < CNum; i++) {
            fruitCount[i] = Integer.parseInt(CArray[i]);
        }

//        int aC = Integer.parseInt(C.split(" ")[0]);
//        int bC = Integer.parseInt(C.split(" ")[1]);

        String SP = in.nextLine();
//        int aP = Integer.parseInt(SP.split(" ")[0]);
//        int bP = Integer.parseInt(SP.split(" ")[1]);
        String[] singleFruitPriceStringArray = SP.split(" ");
        int singleFruitPriceNum = singleFruitPriceStringArray.length;
        int[] singleFruitPriceArray = new int[singleFruitPriceNum];
        for (int i = 0; i < singleFruitPriceNum; i++) {
            singleFruitPriceArray[i] = Integer.parseInt(singleFruitPriceStringArray[i]);
        }



        int[][] p = new int[10][CNum + 1];
        int pNum = 0;
        while (in.hasNextLine()) {
            String noS = in.nextLine();
            if (noS == null || noS.isEmpty()) break;
            String[] no = noS.split(" ");
            for (int i = 0; i <= CNum; i++) {
                p[pNum][i] = Integer.parseInt(no[i]);
            }
            pNum++;

        }

        int minMoney = Integer.MAX_VALUE;

        for (int i = 0; i < Math.pow(2, pNum); i++){
            int[] nums = new int[CNum];

            for (int x  = 0; x < CNum; x++) nums[x] = fruitCount[x];
//            int flag = 0;
//            int numB = bC;

            int count = 0;

            for (int j = 0; j < pNum; j++){
                if((i & (1 << j)) != 0){
                    int flag = 0;
                    for (int k = 0; k < CNum; k++) {
                        nums[flag++] -= p[j][k];
                        count += p[j][k];
//                            flag = 0;
                    }
                }
            }

            for (int j = 0; j < CNum; j++) {
                if (nums[j] >= 0){
                    count += nums[j] * singleFruitPriceArray[j];
                }
            }

//            count += numA * aP + numB * bP;


//            if(numA <= 0 && numB <= 0){
                minMoney = Math.min(minMoney, count);
//            }
        }

        System.out.println(minMoney);


        


    }
}
