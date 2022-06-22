package com.example.playandroid_kt.rengwuxian.lesson1

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
open class Food(foodName: String, produceArea: String) {

    var foodName: String? = foodName
        get() {
            return "foodName is : $field"
        }
        set(value) {
            field = "CH:$value"
        }

    var produceArea: String? = produceArea
        get() {
            return field
        }

}