package cn.learn.android.myapplication.javacommukt

import android.content.Intent
import java.io.BufferedReader
import java.lang.NumberFormatException
import java.nio.Buffer

/**
 * @description
 * @author ch
 * @date 2021/9/1
 * @edit
 */
data class KTStudent(val name: String, val age: Int)

//abstract class IParent {
//
//    abstract val name: String
//
//    abstract val age: Int
//
//    open val sex: String = "2"
//
//    open fun print() {}
//}
//
//open class ChildImpl(override val name: String) : IParent() {
//
//    override val age: Int by lazy { 22 }
//
//    final override fun print() {
//    }
//
//    final override val sex: String = "22"
//
//    var isMarried: Boolean = false
//
//}

class Rectangle(val width: Int, val height: Int) {

    var isSquare: Boolean? = null

}

fun main() {

    val list = listOf(1, 2, 3)
    list.withIndex()
}

fun readNum(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println("num:$number")
}








