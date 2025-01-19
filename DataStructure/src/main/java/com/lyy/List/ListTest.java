package com.lyy.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> IntegerList1 = new ArrayList<>();
        Integer[] num2 = new Integer[]{1,2,3,4,5};
        List<Integer> integers = new ArrayList<>(Arrays.asList(num2));

        int num = integers.get(1);
        System.out.println("num = " + num);
        integers.set(1,0);
        num = integers.get(1);
        System.out.println("num = " + num);
    }
}
