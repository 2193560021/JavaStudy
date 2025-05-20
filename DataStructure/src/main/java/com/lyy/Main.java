package com.lyy;

import java.util.ArrayList;
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
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    static void add(List<Integer> res){
        res.add(123);
    }
}