package com.lyy.ioc;

public class Test {

    public static void main(String[] args) {
        DataConfig dataConfig = new DataConfig();
        dataConfig.setDriverName("Driver");
        dataConfig.setUrl("localhost:3306/dbname");
        dataConfig.setUserName("root");
        dataConfig.setPassWord("123456");
    }
}
