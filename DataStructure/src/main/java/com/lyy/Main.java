package com.lyy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        ReentrantLock lock = new ReentrantLock();
////        ThreadPoolExecutor executor = new ThreadPoolExecutor();
//        Integer i = 1;
//
//        List<Integer> res = new ArrayList<>();
//        add(res);
//
//        res.forEach(System.out::println);
//        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadLocal<Integer> tl = new ThreadLocal<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("dateFormat = " + dateFormat.getCalendar().getTime());
        String currentDate = dateFormat.format(calendar.getTime());
        System.out.println("当前日期：" + currentDate);

        //获取当前时间戳
        long timestamp = System.currentTimeMillis();
        System.out.println("当前时间戳：" + timestamp);

        boolean b = true;
        System.out.println("String.valueOf(b) = " + String.valueOf(b));

    }

//    static void add(List<Integer> res){
//        res.add(123);
//    }
}