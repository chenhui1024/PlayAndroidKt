package com.example.playandroid_kt.rengwuxian.lesson1

import com.example.playandroid_kt.rengwuxian.lesson1.Apple

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
class BigApple(foodName: String, produceArea: String) : Apple(foodName, produceArea) {

    constructor() : this("大苹果", "中国") {
    }

    fun getWeight(): Float {
        return weight()
    }

}
