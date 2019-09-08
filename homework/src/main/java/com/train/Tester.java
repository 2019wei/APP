package com.train;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Please enter number of tickets: ");
        Scanner scanner = new Scanner(System.in);
        Integer ticket =  scanner.nextInt();
        System.out.println("How many round-trip tickets:  ");
        Integer goback =  scanner.nextInt();
        Purchase purchase = new Purchase(ticket,goback);
        purchase.print();


    }
}
