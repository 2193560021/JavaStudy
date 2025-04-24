package com.lyy.ioc;

import com.lyy.ioc.aop.Cal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.lyy.ioc");
        Cal bean = context.getBean(Cal.class);
        System.out.println(bean.add(8, 9));
    }
}
