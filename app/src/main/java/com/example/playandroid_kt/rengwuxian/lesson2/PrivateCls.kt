package com.example.playandroid_kt.rengwuxian.lesson2


/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
class PrivateCls {

    private constructor() {
    }

    var name = "PrivateCls"

    companion object {

        fun getInstance(): PrivateCls {
            return PrivateCls()
        }

    }

}