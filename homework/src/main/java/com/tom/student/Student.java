package com.tom.student;

public class Student{
    String id;
    String name;
    Integer english;
    Integer math;
    static int pass = 60;

    public Student(String name, Integer english, Integer math) {
        this.name = name;
        this.english = english;
        this.math = math;

    }

    public int highest(){
        int max = (english > math) ? english : math;
//        if (english > math){
//            max = english;
//        }
//        else {
//         max = math;
//        }

        return max;
    }

    public void  print(){
        System.out.print(name + "\t" + english + "\t" + math + "\t" +
                getAverage());
        if (getAverage()>=pass) {
            System.out.println("\tPass");
        }else
        {
            System.out.println("\tFailed");
        }
    }

    public int getAverage(){
        return (math+english)/2;
    }
}
