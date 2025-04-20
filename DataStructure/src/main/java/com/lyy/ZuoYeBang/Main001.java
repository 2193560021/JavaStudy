package com.lyy.ZuoYeBang;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String no1 = in.nextLine();
        String find = no1.split(" ")[1];
        String[] no = no1.split(" ")[0].split("\\?");


        String[] tmp = no[1].split("&");

        int len = tmp.length;

        Map<String, String> map = new HashMap<>();

        for(int i = 0; i < len; i++){
            String key = tmp[i].split("=")[0];
            String val = tmp[i].split("=")[1];
            map.put(key, val);
        }

        System.out.println(map.get(find));



    }
}
