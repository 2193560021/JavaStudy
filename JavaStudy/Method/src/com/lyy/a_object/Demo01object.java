package com.lyy.a_object;

public class Demo01object {
    public static void main(String[] args) {
        Cooking cooking = new Cooking();
        cooking.name = "红烧肉";

        cooking.execute();

    }

}

class Cooking{
    String name;

    String price;

     public void execute(){
        System.out.println("开始" + name);

    }
}
