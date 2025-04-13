package com.lyy.xfusion;

import java.util.Scanner;

public class Main02_01 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        String C = in.nextLine();
//        String[] CArray = C.split(" ");
//        int CNum = CArray.length;
//        int[] fruitCount = new int[CNum];
//        for (int i = 0; i < CNum; i++) {
//            fruitCount[i] = Integer.parseInt(CArray[i]);
//        }

        int aC = Integer.parseInt(C.split(" ")[0]);
        int bC = Integer.parseInt(C.split(" ")[1]);

        String SP = in.nextLine();
        int aP = Integer.parseInt(SP.split(" ")[0]);
        int bP = Integer.parseInt(SP.split(" ")[1]);
//        String[] singleFruitPriceStringArray = P.split(" ");
//        int singleFruitPriceNum = singleFruitPriceStringArray.length;
//        int[] singleFruitPriceArray = new int[singleFruitPriceNum];
//        for (int i = 0; i < singleFruitPriceNum; i++) {
//            singleFruitPriceArray[i] = Integer.parseInt(singleFruitPriceStringArray[i]);
//        }



        int[][] p = new int[10][3];
        int pNum = 0;
        while (in.hasNextLine()) {
            String noS = in.nextLine();
            if (noS == null || noS.isEmpty()) break;
            String[] no = noS.split(" ");

            if (no.length == 3) {
                p[pNum][0] = Integer.parseInt(no[0]);
                p[pNum][1] = Integer.parseInt(no[1]);
                p[pNum][2] = Integer.parseInt(no[2]);
                pNum++;
            }
        }

        int minMoney = Integer.MAX_VALUE;

        for (int i = 0; i < Math.pow(2, pNum); i++) {
            int numA = aC;
            int numB = bC;

            int count = 0;

            for (int j = 0; j < pNum; j++) {
                if ((i & (1 << j)) != 0) {
                    numA -= p[j][0];
                    numB -= p[j][1];
                    count += p[j][2];
                }
            }

            if (numA < 0 && numB < 0) {
                break;
            }

            count += numA * aP + numB * bP;

            minMoney = Math.min(minMoney, count);
        }

        System.out.println(minMoney);


        


    }
}
