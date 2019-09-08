package com.tom.student;

public class GraduateStudent extends Student {
    int thesis;
    static int pass = 70;
    public GraduateStudent(String name,int english,int math,int thesis){
        super(name,english,math);
        this.thesis = thesis;
    }

    @Override
    public void print() {
        System.out.print(name + "\t" + english + "\t" + math + "\t" + thesis + "\t"+
                getAverage());
        if (getAverage()>=pass) {
            System.out.println("\tPass");
        }else
        {
            System.out.println("\tFailed");
        }
    }
}
