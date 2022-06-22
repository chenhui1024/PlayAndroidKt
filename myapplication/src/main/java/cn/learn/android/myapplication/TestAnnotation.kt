package cn.learn.android.myapplication

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * @description
 * @author ch
 * @date 2021/9/22
 * @edit
 */
fun main() {
    val function = ::privateTest

    val personCls = People::class
    val members = personCls.members
    for (member in members) {
        println(member.name)
    }
    println("isValue:${personCls.isValue}")
    println("isData:${personCls.isData}")
    println("nestedClasses:${personCls.nestedClasses}")
}

private fun privateTest(message: String): Int {
    return message.toInt()
}

class AnnotationImpl {

    //注解作用到getter方法上
    @get:MyAn
    var name: String = ""

    //注解到set方法上
    @set:MyAn
    var age: Int = 20

    //注解到属性本身上d
    @field:MyAn
    var sex: Char = 'M'
}

//@Target(AnnotationTarget.FIELD)
annotation class MyAn {

}
