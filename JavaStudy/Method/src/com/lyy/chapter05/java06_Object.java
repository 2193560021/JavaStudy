package com.lyy.chapter05;

import java.text.SimpleDateFormat;
import java.util.Date;

public class java06_Object {
    public static void main(String[] args) {

        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(d);
        System.out.println(dateString);

    }
}