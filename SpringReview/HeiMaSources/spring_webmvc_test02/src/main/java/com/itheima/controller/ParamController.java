package com.itheima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.User;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class ParamController {

    @RequestMapping("/param14")
    public String param14(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);
        System.out.println(response);
        return "/index.jsp";
    }


    @GetMapping("/request1")
    public String request1(HttpServletRequest request){
        //向request域中存储数据
        request.setAttribute("name","haohao");
        return "/request2";
    }

    @GetMapping("/request2")
    public String request2(@RequestAttribute("name") String name){
        System.out.println(name);
        return "/index.jsp";
    }


    @GetMapping("/param13")
    public String param13(@CookieValue("JSESSIONID") String jsessionid){
        System.out.println(jsessionid);
        return "/index.jsp";
    }


    @GetMapping("/param12")
    public String param12(@RequestHeader Map<String,String> map){
        map.forEach((k,v)->{
            System.out.println(k+"==>"+v);
        });
        return "/index.jsp";
    }

    @GetMapping("/param11")
    public String param11(@RequestHeader("Accept-Encoding") String headerValue){
        System.out.println(headerValue);
        return "/index.jsp";
    }


    @PostMapping("/param10")
    public String param10(@RequestBody MultipartFile myFile) throws IOException {
        System.out.println(myFile);
        //将上传的文件进行保存
        //1、获得当前上传的文件的输入流
        InputStream inputStream = myFile.getInputStream();
        //2、获得上传文件位置的输出流
        OutputStream outputStream = new FileOutputStream("C:\\Users\\haohao\\"+myFile.getOriginalFilename());
        //3、执行文件拷贝
        IOUtils.copy(inputStream,outputStream);

        //4、关闭流资源
        inputStream.close();
        outputStream.close();

        return "/index.jsp";
    }


    //http://localhost/user/100 =>根据id查询
    @GetMapping("/user/{id}/{xxx}")
    public String findUserById(
            @PathVariable("id") int id,
            @PathVariable("xxx") String yyy
    ){
        System.out.println("id==>"+id);
        System.out.println("yyy==>"+yyy);
        return "/index.jsp";
    }


    //http://localhost/param9
    @PostMapping("/param9")
    public String param9(@RequestBody User user) throws JsonProcessingException {
        System.out.println(user);
        return "/index.jsp";
    }


    //http://localhost/param8
    @PostMapping("/param8")
    public String param8(@RequestBody String body) throws JsonProcessingException {
        //使用jackson进行转换,将json格式的字符串转换成User对象
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(body, User.class);
        System.out.println(user);
        return "/index.jsp";
    }

    //http://localhost/param7
    @PostMapping("/param7")
    public String param7(@RequestBody String body){
        System.out.println(body);
        return "/index.jsp";
    }


    //http://localhost/param6?username=zhangsan&age=18&hobbies=zq&hobbies=pq&birthday=2018/11/11&address.city=tj&address.area=bh
    @GetMapping("/param6")
    public String param6(User user){
        System.out.println(user);
        return "/index.jsp";
    }


    //http://localhost/param5?username=zhangsan&age=18
    @GetMapping("/param5")
    public String param5(@RequestParam Map<String,String> map){
        map.forEach((k,v)->{
            System.out.println(k+"==>"+v);
        });
        return "/index.jsp";
    }


    //http://localhost/param4?hobby=zq&hobby=pq&hobby=tq
    @GetMapping("/param4")
    public String param4(@RequestParam List<String> hobby){
        for (String s : hobby) {
            System.out.println(s);
        }
        return "/index.jsp";
    }


    //http://localhost/param3?hobby=zq&hobby=pq&hobby=tq
    @GetMapping("/param3")
    public String param3(String[] hobby){
        for (String s : hobby) {
            System.out.println(s);
        }
        return "/index.jsp";
    }


    //http://localhost/param2?username=zhangsan&age=18
    @GetMapping("/param2")
    public String param2(@RequestParam(value="username",required = false,defaultValue = "haohao") String name, Integer age){
        System.out.println(name+"===="+age);
        return "/index.jsp";
    }

    //http://localhost/param1?username=zhangsan&age=18
    @RequestMapping("/param1")
    public String param1(String username,int age){
        System.out.println(username+"===="+age);
        return "/index.jsp";
    }



}
