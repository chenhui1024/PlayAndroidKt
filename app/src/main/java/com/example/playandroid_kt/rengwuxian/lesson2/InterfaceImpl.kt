package com.example.playandroid_kt.rengwuxian.lesson2

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
object InterfaceImpl: BaseCls(), IInterface {

    override fun getName(): String {
       return "i am InterfaceImpl"
    }
}