package com.tom.guess;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secret = random.nextInt(10) + 1;
        System.out.println(secret);
        int number = 0;
        while (number != secret) {
            System.out.println("plz ");
            number = scanner.nextInt();
            System.out.println(number);
            if (number < secret) {
                System.out.println("higher");
            } else if (number > secret) {
                System.out.println("lower");
            } else {
                System.out.println("Great");
            }
        }
    }
}