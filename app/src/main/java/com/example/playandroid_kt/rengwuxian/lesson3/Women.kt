package com.example.playandroid_kt.rengwuxian.lesson3

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/7/4
 * @edit
 */
class Women(name: String, age: Int) {

    var name: String = name
    var age = age

    init {
        Log.d("Women", "把上面的field注释了，这里也能访问到name:$name")
    }

}