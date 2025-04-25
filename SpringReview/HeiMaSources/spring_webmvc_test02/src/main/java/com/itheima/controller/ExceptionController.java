package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class ExceptionController {

    @RequestMapping("/ex1")
    public String exceptionMethod(){
        int i = 1/0;
        return "Hello Exception";
    }

    @RequestMapping("/ex2")
    public String exceptionMethod2() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:xxx/xxx.xxx");
        return "Hello Exception";
    }

}
