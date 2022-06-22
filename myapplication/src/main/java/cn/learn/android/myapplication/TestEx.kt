package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/15
 * @edit
 */

fun main() {
//    val parent = Parent()
//    println("parent name :${parent.name}")

    val string = "11|1.222.333"
    for (s in string.split(".", "|", limit = 10)) {
        println(s)
    }
}

open class Parent

class Child : Parent() {
}

fun Parent.log(str: String): Unit = println("parent log:$str")
fun Child.log(str: String): Unit = println("child log:$str")

val Parent.name: String
get() {
    return "111"
}



