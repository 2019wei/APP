package com.tom

import java.util.*

fun main(args: Array<String>) {
    val secret = Random().nextInt(10)+1

    val scanner = Scanner(System.`in`)
    var  number = 0
    print(secret)
    while (number != secret) {
        print("plz num")
        //number = scanner.nextInt()
        number = readLine()!!.toInt()
        if (number > secret) {
            print("lower")
        } else if (number < secret) {
            print("higher")
        } else {
            print("great")
        }
    }
}