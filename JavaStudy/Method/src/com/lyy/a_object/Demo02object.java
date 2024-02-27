package com.lyy.a_object;

public class Demo02object {
    public static void main(String[] args) {
        Teacher t = new Teacher();
        t.name = "LYY";
        Student s = new Student();
        s.name = "LX";
        t.teach();
        s.study();

    }

}

class Teacher{
    String name;

    void teach(){
        System.out.println(name + "讲课");
    }
}

class Student{
    String name;

    void study(){
        System.out.println(name + "听课");
    }
}
