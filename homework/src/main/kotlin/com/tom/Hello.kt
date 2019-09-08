package com.tom

fun main(args: Array<String>) {
//    println("Hello kotlin")
//    Human().hello()
    val h = Human(66.5f,1.7f)
    h.hello();
    println(h.bmi())

}

class Human( var weight:Float,var height:Float,var name:String=""){
    init {
        println("test $weight")
    }
//    constructor(name:String,weight: Float,height: Float) :this(weight,height)
    fun bmi():Float{
        val bmi = weight/(height*height)
        return bmi
    }
    fun hello(){
        println("Hello kotlin")
    }
}