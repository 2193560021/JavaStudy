package com.lyy.a_object;

import java.lang.Object;

public class Demo17object_01 {
    public static void main(String[] args) {
        /* public:公共的，访问权限修饰符
            公共类只能有一个，而且必须与源码文件名相同
            mian方法是JVM调用的

            private：私有的，同一个类
            (default)：默认权限，同包可以使用
            protected：受保护的权限，子类可以使用，不管是否同包
            public：公共的
                */
        Person17 p = new Person17();


    }

}

 class Person17 extends java.lang.Object{
    String name;

    void test() throws Exception {
        clone();
    }
}

