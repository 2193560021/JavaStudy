package com.lyy.xfusion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main01 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        Map<String, Integer> map = new HashMap<>();
        while(in.hasNextLine()){
            String noS = in.nextLine();
            if(noS == null || noS.isEmpty()) break;
            String[] no = noS.split(",");

            if(no.length == 2){
                String nowName = no[0];
                int nowNum = Integer.parseInt(no[1]);
                map.put(nowName, map.getOrDefault(nowName, 0) + nowNum);
            }
        }

        int warningNum = 0;

        for (int count : map.values()){
            if(count >= 10){
                warningNum++;
            }
        }

        System.out.println(warningNum);
    }
}
