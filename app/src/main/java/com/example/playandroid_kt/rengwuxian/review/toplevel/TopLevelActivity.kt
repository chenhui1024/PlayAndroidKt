package com.example.playandroid_kt.rengwuxian.review.toplevel

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import kotlinx.coroutines.*


fun String.printMessage(age: Int): Unit {
    Log.d("chenhui", "$this age is:$age")
}

fun printAndroidLog(tag: String, message: String) {
    Log.d(tag, message)
}

/**
 * @description
 * @author ch
 * @date 2021/3/30
 * @edit
 */
class TopLevelActivity : AppCompatActivity() {

    private var intervalFunc: (Interval, String) -> Unit? = fun(interval: Interval, name: String): Unit {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)
        printAndroidLog("chenhui", "printAndroidLog")
        "chenhui".printMessage(29)
        val printExMethod = String::printMessage
        printExMethod("111", 2)
        (String::printMessage)("222", 2)
        (String::printMessage).invoke("333", 3)
        printLog((String::printMessage), "444", 29)

        (Interval::print)(Interval(), "111")

        //类的成员函数可以赋值给普通函数对象，不过需要在第一个参数增加类对象
        intervalFunc = Interval::print

        //类的扩展函数也可以赋值给普通函数对象，同样需要在第一个参数增加类对象
        val stringFunc: (String, Int) -> Unit = String::printMessage
        stringFunc.invoke("555", 29)

        //也可以将普通函数赋值给类的拓展函数
        val stringMehtod: String.(Int) -> Unit = ::stringPrintLog
        "chenhui".stringMehtod(29)
    }

    private fun stringPrintLog(instance: String, age: Int): Unit {

    }

    private fun printLog(function: (String, Int) -> Unit, instance: String, age: Int) {
        function(instance, age)
        function.invoke(instance, age)
    }

    class Interval {

        fun print(str: String): Unit {
            Log.d("chenhui", "Interval print:$str")
        }

    }

}