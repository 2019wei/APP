package com.Kolin

import java.util.*

fun main(args: Array<String>) {
    //userinput()

    val stu = Student("hank", 77, 99)
    val stu1 = Student("jam", 44, 68)
    val stu2 = Student("lon", 30, 49)
    val gstu = GraduateStudent("jack",55,65,60)
    gstu.print()
    stu.print()
    stu1.print()
    stu2.print()
    println(stu.highest())
}

class  GraduateStudent(name: String?,english: Int, math: Int,thesis:Int) : Student(name,english,math) {
    companion object {
        var pass = 70
    }

    override fun print() {
        println(name + "\t" + english + "\t" + math + "\t" + getaverage() +"\t"+ if(getaverage()>= pass) "Pass" else "Failed")
    }

}

open class Student(var name:String?,var english:Int,var math:Int) {
    companion object {
        @JvmStatic
        var pass = 60
        fun test(){
            println("testing")
        }
    }
   open fun print(){
        println(name + "\t" + english + "\t" + math + "\t" + getaverage() +"\t"+ if(getaverage()>= pass) "Pass" else "Failed")
        println(grading())
    }
    fun getaverage():Int{
        return (english+math)/2
    }
    fun grading() : Char{
        var grading : Char = when(getaverage()){
            in 90..100 -> 'A'
            in 80..89 -> 'B'
            in 70..79 -> 'C'
            in 60..69 -> 'D'
            else -> 'F'
        }
        return grading
    }

    fun highest() : Int{
        var max = if (english>math)
        {   println("english")
            english}
        else {println("math")
            math}
        return max
    }

private fun userinput() {
    val scanner = Scanner(System.`in`)
    print("please enter name")
    var name = scanner.next()
    print("please enter english")
    var english = scanner.nextInt()
    print("please enter math")
    var math = scanner.nextInt()
    val stu = Student(name, english, math)
    stu.print()
}



}