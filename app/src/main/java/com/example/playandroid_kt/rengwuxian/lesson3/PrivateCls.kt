package com.example.playandroid_kt.rengwuxian.lesson3

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/7/3
 * @edit
 */
class PrivateCls private constructor() {
    //正常来说主构造器是可以省略的，但是如果被其他操作符修饰了，就无法省略。比如这里的private
    //修饰普通函数与修饰构造器的用法是一样的

    companion object {

        fun getInstance(): PrivateCls = PrivateCls()

    }

}