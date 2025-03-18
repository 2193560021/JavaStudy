package com.lyy.Lambda;

import cn.hutool.core.io.LineHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class test {
    
    @Test
    public void test01(){
        try {
            int i = 0;
            while (i < 5) {
                i++;
                if (i == 3) {
                    continue; // 跳过本次循环的剩余代码
                }
                System.out.println("i=" + i);
                // 输出：2, 4, 5
            }

            System.exit(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }

    }
}
