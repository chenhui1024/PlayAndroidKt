package com.example.playandroid_kt.rengwuxian

import android.app.Application
import jetpack.room.AppDataBase

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDataBase.init(this)
    }

}