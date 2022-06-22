package com.example.playandroid_kt.rengwuxian.review

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2021/3/29
 * @edit
 */
open class Student constructor(var name: String) {

    var age: Int? = null
    var sex: String? = null

    constructor(name: String, age: Int) : this(name, 20, "man") {
    }

    constructor(name: String, age: Int, sex: String) : this(name) {
        this.age = age
        this.sex = sex
    }

    fun show() {
        Log.d("chenhui", "i am $name, age is $age, sex is $sex")
    }

}