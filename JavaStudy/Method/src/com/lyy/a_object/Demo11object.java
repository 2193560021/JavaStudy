package com.lyy.a_object;

public class Demo11object {
    public static void main(String[] args) {

        Person p = new Person();
        p.testPerson();
        Person p1 = new Boy();
        p1.testPerson();
        Boy p11 = new Boy();
        p11.testBoy();
        Person p2 = new Girl();
        p2.testPerson();

    }
}
class Person{
    void testPerson(){
        System.out.println("test Person...");
    }
}

class Boy extends Person{
    void testBoy(){
        System.out.println("test Boy...");
    }
}


class Girl extends Person{
    void testGirl(){
        System.out.println("test Girl...");
    }
}
