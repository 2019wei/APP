package com.tom.train

import com.train.Purchase
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    println("Please enter number of tickets: ")
    var ticket =  scanner.nextInt()
    println("How many round-trip tickets:  ")
    var goback =  scanner.nextInt()
//    var pur = Purchase(ticket,goback)
//    pur.print()
    var pur = purchase(ticket,goback)
    pur.print()
}
class purchase(var ticket:Int,var goback:Int){
    fun print(){
        val total = ((ticket - goback) * 1000 + goback.toDouble() * 2000.0 * 0.9).toInt()
        println("Total tickets:" + ticket )
        println("Round-trip: " + goback)
        println("Total:" + total)
    }
}