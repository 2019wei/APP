package com.tom;

public class FinanceReport implements Report{

    @Override
    public void load() {
        System.out.println("finance loading data");
    }

    @Override
    public void print() {
        System.out.println("finance printing data");
    }
}
