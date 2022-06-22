package com.example.playandroid_kt.rengwuxian.lesson3

/**
 * @description
 * @author ch
 * @date 2020/7/4
 * @edit
 */
class Man(var name: String?) {

    var age: Int? = 0
    get() {
        return field?.plus(10)
    }


}