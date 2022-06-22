package cn.learn.android.myapplication

import java.lang.IndexOutOfBoundsException
import java.time.LocalDate

/**
 * @description
 * @author ch
 * @date 2021/9/17
 * @edit
 */
fun main() {
//    val point1 = Point(1, 2)
//    val point2 = Point(3, 4)
//    var point2: Point = Point(3, 4)
//    point2 += point1
//    val result = point1 + point2
//    println("result:$point2")

//    val list = arrayListOf(1, 2)
//    list += 3

    /**
     *   重载运算符“[]”
     */
//    val point = MutablePoint(1, 2)
//    println("point x:${point[0]}, y:${point[1]}")

    /**
     *   重载运算符“in”
     */
//    val rectangle = Rectangle(Point(0, 0), Point(100, 100))
//    val inPoint = Point(50, 50)
//    val outPoint = Point(110, 110)
//    println("inPoint:${inPoint in rectangle}")
//    println("outPoint:${outPoint in rectangle}")
}


/**
 *   重载运算符 “+”
 */
data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

}

/**
 *   重载运算符 "[]"
 */
data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.get(index: Int): Int = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

/**
 *   重载运算符 “in”
 */
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x
            && p.y in upperLeft.y until lowerRight.y
}
