package com.lyy.a_object;

public class Demo22object {
    public static void main(String[] args) {

        //抽象类: 不完整的类，就是抽象类
        //  因为类不完整，无法构建对象
        // 抽象类无法直接构建对象，但可以通过子类简介构建对象
//        Person21 person21 = new Person21();
        Computer c= new Computer();
        Light light1 = new Light();
        Light light2 = new Light();
        c.usb1 = light1;
        c.usb2 = light2;
        c.powerSupply();


    }
}

interface USB22{

}

interface USBSupply extends USB22{
    public void powerSupply();
}

interface USBReceive extends USB22{
    public void powerReceive();
}

class Computer implements USBSupply{

    public USBReceive usb1;
    public USBReceive usb2;


    public void powerSupply(){
        System.out.println("电脑提供能源");
        usb1.powerReceive();
        usb2.powerReceive();
    }
}

class Light implements USBReceive{
    public void powerReceive(){
        System.out.println("电灯接受电源");
    }
}