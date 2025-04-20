package com.lyy.HONOR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main003_01 {
    static class TimeS{
        String s;
        LocalDateTime times;

        public TimeS(String s, LocalDateTime times) {
            this.s = s;
            this.times = times;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        List<TimeS> tmp = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");

        for (String s : arr) {
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                LocalDateTime time = LocalDateTime.parse(matcher.group());
                tmp.add(new TimeS(s, time));
            }
        }

        Comparator<TimeS> comparator = (a, b) ->{
            int cmp = a.times.compareTo(b.times);
            if(cmp != 0) return cmp;
            cmp = Integer.compare(a.s.length(), b.s.length());
            if(cmp != 0) return cmp;
            return a.s.compareTo(b.s);
        };

        Collections.sort(tmp, comparator);

        String p = null;
        for (TimeS timeS : tmp) {
            String now = timeS.s;
            if(!now.equals(p)){
                System.out.println(timeS.s);
                p = now;
            }
        }
    }
}
