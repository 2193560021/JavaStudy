package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/users")
public class UserController {



    @PostMapping
    public String save(@RequestBody User user){
        System.out.println("user save..." + user);
        return "{module:user save}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        System.out.println("user delete..." + id);
        return "{module:user delete}";
    }

    @PutMapping
    public String update(@RequestBody User user){
        System.out.println("user update..." + user);
        return "{module:user save}";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println("user getById..." + id);
        return "{module:user getById}";
    }

    @GetMapping
    public String getAll(){
        System.out.println("user getAll...");
        return "{module:user getAll}";
    }





    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println("user listParamForJson..." + user);
        return "{module:user listParamForJson}";
    }



    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User>  user){
        System.out.println("user listParamForJson..." + user);
        return "{module:user listParamForJson}";
    }


    @RequestMapping("/Datatime")
    @ResponseBody
    public String Datatime(@DateTimeFormat(pattern = "yyyy-mm-dd")Date date){
        System.out.println("yyyy-mm-dd..." + date);
        return "{yyyy-mm-dd...}" + date;
    }


    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面 ");
        return "index.html";
    }

    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name, int age){
        System.out.println("user save..." + name + age);
        return "{module:user save}" + name;
    }

}
