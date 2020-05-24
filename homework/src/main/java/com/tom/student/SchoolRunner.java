package com.tom.student;

import java.util.Scanner;

public class SchoolRunner {
    public static void main(String[] args) {
        com.Kolin.Student.setPass(60);
        com.Kolin.Student stu5 = new com.Kolin.Student("ki",50,90);
        Student stu2 = new Student("tom",60,40);
        Student stu3 = new Student("jay",30,40);
        Student.pass = 50;
        System.out.println("Please enter student's name");
        Scanner scanner = new Scanner(System.in);
        //String name =  scanner.next();
        Student sud = new Student("hank",77,99);
        GraduateStudent gstu = new GraduateStudent("jack",55,65,60);
        gstu.print();
        sud.print();
        stu2.print();
        stu3.print();
        stu5.print();
        System.out.println(sud.highest());
    }
}
