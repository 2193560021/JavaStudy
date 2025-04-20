package com.lyy.HONOR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Map<String, LocalDateTime> tmp = new HashMap<>();

        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");

        for (String s : arr) {
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                LocalDateTime time = LocalDateTime.parse(matcher.group());
                tmp.put(s, time);
            }
        }







    }
}
