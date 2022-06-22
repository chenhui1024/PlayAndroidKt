package com.example.playandroid_kt.rengwuxian.lesson4

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 */
interface ICallBackListener {

    fun onSuccess()

    fun onError(code: String, errorDesc: String)

}