package com.lyy.a_object;

public class Demo12object {
    public static void main(String[] args) {

        User12 u = new User12();
        u.login(111111);
        u.login("1231231231","12312321");
        u.login("1231231231");

    }
}
class User12{

    User12(){
        System.out.println("user12...");
    }

    User12(String name){
        System.out.println("user12..." + name);
    }

    void login(String account, String password){
        System.out.println("账号密码登录...");
    }
    void login(int tel){
        System.out.println("手机验证码登录...");

    }
    void login(String token){
        System.out.println("微信登录...");
    }
}
