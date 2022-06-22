package com.example.playandroid_kt.rengwuxian.lesson4

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 */
class FruitProducer : Producer<Fruit> {

    companion object {
        private const val TAG = "FruitProducer"
    }

    override fun produce(name: String): Fruit {
        Log.d(TAG, "我生产了一个:$name")
        return when (name) {
            "苹果" -> {
                Apple()
            }
            "香蕉" -> {
                Banana()
            } else -> {
                OtherFruit()
            }
        }
    }
}