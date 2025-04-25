package com.itheima.ex;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeExceptionResolverMethod(RuntimeException ex){
        System.out.println(ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error1.html");
        return modelAndView;
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result ioExceptionResolverMethod(IOException ex){
        System.out.println(ex);
        Result result = new Result(0,"xxxx","xxxx");
        return result;
    }

}
