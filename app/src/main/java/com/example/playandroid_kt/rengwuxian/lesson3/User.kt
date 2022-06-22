package com.example.playandroid_kt.rengwuxian.lesson3

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/7/3
 * @edit
 */
//主构造函数
class User constructor(var name: String?) {

    companion object {
        private const val TAG = "User"
    }

    var age: Int? = null

    constructor(age: Int, name: String) : this(name) {
        this.age = age
        Log.d(TAG, "次构造函数被执行 === > ")
    }

    constructor(person: Person) : this(person.name) {
    }

    constructor() : this("chenhui")

    init {
        //会在主构造函数之后执行，每次new对象的时候，都会被调用
        Log.d(TAG, "init代码块:$name")
    }

}