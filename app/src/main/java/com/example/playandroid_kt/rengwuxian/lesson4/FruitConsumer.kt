package com.example.playandroid_kt.rengwuxian.lesson4

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 */
class FruitConsumer<T : Fruit> : Consumer<T> {

    companion object {
        private const val TAG = "FruitConsumer"
    }

    override fun consume(t: T) {
        Log.d(TAG, "我消费了一个:${t.name}")
    }
}