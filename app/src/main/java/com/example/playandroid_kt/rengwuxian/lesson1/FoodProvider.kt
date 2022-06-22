package com.example.playandroid_kt.rengwuxian.lesson1

import android.util.Log
import com.example.playandroid_kt.rengwuxian.lesson1.ContentProvider

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 */
abstract class FoodProvider: ContentProvider {

    override fun createObject() {
        Log.d("chenhui", "FoodProvider createObject")
    }

}