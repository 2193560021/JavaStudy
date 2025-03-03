package com.lyy.IO;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test01 {

    @Test
    public void test01() throws IOException {
        try {
            String chineseChar = "中";
            byte[] bytes = chineseChar.getBytes("UTF-8");
            System.out.println("在UTF-8编码下，一个中文字的字节数: " + bytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test02() throws IOException {
        InputStream inputStream = new FileInputStream("F:\\Code\\JavaStudy\\DataStructure\\src\\main\\java\\com\\lyy\\IO\\lyy.txt");
        System.out.println("fileReader = " + inputStream);
        int read = inputStream.read();
        System.out.println("read = " + read);
        System.out.println(inputStream.available());
    }

    @Test
    public void test03() throws IOException {
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(() -> System.out.println("线程池任务执行"));
//        executor.shutdown(); // 关闭线程池
    }

}
