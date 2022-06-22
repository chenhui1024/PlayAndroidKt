package com.example.playandroid_kt.rengwuxian.lesson1

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
open class Apple(foodName: String, produceArea: String) : Food(foodName, produceArea) {

    constructor() : this("苹果", "中国") {
    }

    init {
        Log.d("chenhui", "apple create")
    }

    protected fun weight(): Float {
        return 3f
    }


}