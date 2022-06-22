package com.example.playandroid_kt.rengwuxian.lesson2

/**
 * 属于 package，不在 class/object 内
 * 这样写的属性和函数，不属于任何 class，而是直接属于 package，
 * 它和静态变量、静态函数一样是全局的，但用起来更方便：你在其它地方用的时候，就连类名都不用写：
 *
 * 当如果两个类中出现一样名字的变量或方法，则会自动加入包名来区分
 *
 * 如果想写工具类的功能，直接创建文件，写 top-level「顶层」函数。
 * 如果需要继承别的类或者实现接口，就用 object 或 companion object。
 */
var DESCRIPTION = "lesson2 descprition"

/**
 * Kotlin 的常量必须声明在对象（包括伴生对象）或者「top-level 顶层」中，因为常量是静态的。
 * Kotlin 新增了修饰常量的 const 关键字。
 */
const val NAME = "TopLevel"

fun getDescprition(): String {
    return DESCRIPTION
}

//仅该项目可用
internal fun getIntervalName(): String {
    return "internal fun called !!!"
}

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
class TopLevel {

    var name = "TopLevel"


}