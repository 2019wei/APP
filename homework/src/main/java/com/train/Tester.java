package com.train;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        int ticket;
        do {
            System.out.println("Please enter number of tickets: ");
            Scanner scanner = new Scanner(System.in);
            ticket = scanner.nextInt();
            if(ticket != -1) {
                System.out.println("How many round-trip tickets:  ");
                Integer goback = scanner.nextInt();
                Purchase purchase = new Purchase(ticket, goback);
                purchase.print();
            }
        }
        while (ticket != -1);
        System.out.println("finish");

    }
}
