package com.lyy.ioc.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
public class LoggerAspect {

    @Before("execution(public int com.lyy.ioc.aop.CalImpl.*(..))")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("执行" + name + "方法，参数是" + Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(value = "execution(public int com.lyy.ioc.aop.CalImpl.*(..))",
    returning = "result")
    public void after(JoinPoint joinPoint, Objects result){
        String name = joinPoint.getSignature().getName();
        System.out.println("执行" + name + "方法，结果是" + result);
    }
}
