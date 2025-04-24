package com.lyy.ioc.aop;

import org.springframework.stereotype.Component;

@Component
public class CalImpl implements Cal{

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int mus(int a, int b) {
        return a - b;
    }
}
