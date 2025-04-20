package com.lyy.HONOR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main001 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String no = br.readLine();
        String[] parts = no.split(" ");
        int n = Integer.parseInt(parts[0]);
        List<String> res = new ArrayList<>();

        for (int p = 1;p <= n; p++) {
            String part = parts[p];
            if(part.isEmpty()) continue;
            int time = 0;
            for(int i = 0; i < part.length();i += 8){
//                int end = Math.max(i, part.length());
                int end = 0;
                if(part.length() > 8){
                    time++;
                    end = 8 * time;
                    end = Math.min(end, part.length());
                }
                else end = part.length();
                String substr = part.substring(i, end);
                while (substr.length() < 8){
                    substr += '0';
                }
                res.add(substr);
            }
        }

        Collections.sort(res);

        for (String re : res) {
            System.out.print(re);
            System.out.print(" ");
        }


    }
}
