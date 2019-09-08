package com.tom;

import org.omg.CORBA.INTERNAL;

public class Hello {
    public static void main(String[] args) {
//        System.out.println("Hello world");
        Person p = new Person("Tom",66.5f,1.7f);

        p.hello();
        p.weight = 66.5f;
        p.height = 1.7f;
        System.out.println(p.bmi());
    }
}
