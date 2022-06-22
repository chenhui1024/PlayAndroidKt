package com.example.playandroid_kt.rengwuxian.lesson6

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R

/**
 * @description
 * @author ch
 * @date 2020/7/8
 * @edit
 *
 * 我们先看匿名函数。它可以作为参数传递，也可以赋值给变量，对吧？

    但是我们刚才也说过了函数是不能作为参数传递，也不能赋值给变量的，对吧？

    那为什么匿名函数就这么特殊呢？

    因为 Kotlin 的匿名函数不——是——函——数。它是个对象。
    匿名函数虽然名字里有「函数」两个字，包括英文的原名也是 Anonymous Function，
    但它其实不是函数，而是一个对象，一个函数类型的对象。它和双冒号加函数名是一类东西，和函数不是。
 *
 *
 */
class Lesson6Activity : AppCompatActivity() {

    private lateinit var callBack: (String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson6)

        //传一个函数。其实这里说明一下，传入的其实是一个函数的引用，并且这个引用不是指向这个函数，
        // 而是指向这个函数所对应的一个对象，这个对象具备该函数的全部功能
        //在 Kotlin 里，一个函数名的左边加上双冒号，它就不表示这个函数本身了，
        // 而表示一个对象，或者说一个指向对象的引用，但，这个对象可不是函数本身，而是一个和这个函数具有相同功能的对象。
        val result = a(::method, 10)
        println("result:$result")

        //函数作为返回值
        val method = b()
        val r = method(20)
        println("method:$r")

        //定义一个函数类型的变量
        var methodFun = fun(num: Int): String {
            println("methodFun被调用 === > ")
            return num.toString()
        }
        methodFun(666)

        setCallBack {
            println("这是listener里面打印出来的内容：$it")
        }
        callBack("ccccc")
    }

    //函数作为参数类型
    //参数是一个函数，这个函数的入参是Int类型，然后返回值是String类型
    fun a(funParam: (Int) -> String, num: Int): String {
        return funParam(num)
    }

    fun method(num: Int): String {
        return num.toString()
    }

    //函数作为返回值
    fun b(): (Int) -> String {
        return ::method
    }

    fun setCallBack(callBack: (String) -> Unit) {
        this.callBack = callBack
    }

}