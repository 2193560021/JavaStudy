package com.lyy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Integer i = 1;

        List<Integer> res = new ArrayList<>();
        add(res);

        res.forEach(System.out::println);

    }

    static void add(List<Integer> res){
        res.add(123);
    }
}