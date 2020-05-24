package com.tom.bmi;

import com.tom.bmi.hello.Person;
import com.tom.bmi.hello.Student;

public class Tester {
    public static void main(String[] args) {
        Student stu = new Student("001","Hank",60,80);
        Student stu1 = new Student("002","nk",60,50);
        stu.print();
        stu1.print();
//        stu.setId("001");
//        stu.setName("tom");
//        stu.setMath(60);
//        stu.setEnglish(80);



//        System.out.println("Hello world");
//        Person person =  new Person();
//        person.hello();
//        person.hello("Tom");
//        person.setWeight(66);
//        person.setHeight(1.7f);
//        System.out.println(person.bmi());
    }
}
