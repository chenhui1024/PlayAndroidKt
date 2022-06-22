package com.example.playandroid_kt.rengwuxian.lesson4

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 */
interface Consumer<in T> {

    fun consume(t: T)

}