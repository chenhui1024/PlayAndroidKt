package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/14
 * @edit
 */
sealed class Color

class Black : Color()
data class Red(val deep: Int) : Color()
class Green : Color()

fun main() {
//    judgeColor(Black())
//    judgeColor(Red(11))

    A("")
}

fun judgeColor(color: Color) {
    when (color) {
        is Black -> println("black == > ")
        is Red -> println("red === > ${color.deep}")
        is Green -> println("green == > ")
    }
}

open class A() {

    init {
        println("init2 === >")
    }

    init {
        println("init === > ")
    }

    constructor(name: String) : this() {
        println("constructor === > ")
    }

}

class B : A {

    constructor() : this("111")

    constructor(name: String) : super(name) {
    }

}
