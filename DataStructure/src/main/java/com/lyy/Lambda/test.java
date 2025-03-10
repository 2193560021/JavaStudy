package com.lyy.Lambda;

import cn.hutool.core.io.LineHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    
    @Test
    public void test01(){
        List list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(6);

        list.forEach(System.out::println);

         
        System.out.println("list = " + list);

    }
}
