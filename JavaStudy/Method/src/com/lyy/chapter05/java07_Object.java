package com.lyy.chapter05;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class java07_Object {
    public static void main(String[] args) {

        System.out.println("一\t二\t三\t四\t五\t六\t七\t");

        Calendar FD = Calendar.getInstance();
        FD.set(Calendar.DAY_OF_MONTH,1);

        int MaxDay = FD.getMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0;i < MaxDay;i++){
            int weekX = FD.get(Calendar.DAY_OF_WEEK);
            int monthY = FD.get(Calendar.DAY_OF_MONTH);
            if(i == 0){
                if(weekX == Calendar.SUNDAY){
                    for(int j = 0 ; j < 6;j++){
                        System.out.println("\t");
                    }
                    System.out.println(monthY);
                }else {
                    System.out.println(monthY);

                }
            }else {
                if(weekX == Calendar.SUNDAY){
                    System.out.println(monthY);
                }else {
                    System.out.print(monthY);
                    System.out.print("\t");
                }
            }
            FD.add(Calendar.DATE,1);

        }


    }
}