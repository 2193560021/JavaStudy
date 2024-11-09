package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping
    public String save(){
        System.out.println("book save...");
        return "{module:book save}";
    }



    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        System.out.println("user save...");
        return "{info:SpringMVC}";
    }
}
