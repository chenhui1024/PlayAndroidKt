package com.example.playandroid_kt.rengwuxian.lesson2

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit：
 * kotlin中的object：创建一个类，并且创建一个这个类的对象。这个就是 object 的意思：对象。
 * Object可以将该类变成静态单例类，里面的方法就是静态方法，属性就是静态属性
 *
 * 这种通过 object 实现的单例是一个饿汉式的单例，并且实现了线程安全。
 */
object SingleInstance {

    private const val TAG: String = "SingleInstance"

    init {
        Log.d(TAG, "SingleInstance init === > ")
    }

    val name = "chenhui"

    fun print() {
        Log.d(TAG, "print === > ")
    }


}