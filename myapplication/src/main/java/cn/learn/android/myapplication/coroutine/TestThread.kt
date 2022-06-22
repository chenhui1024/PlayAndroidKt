package cn.learn.android.myapplication.coroutine

import cn.learn.android.myapplication.IA
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2021/10/18
 * @edit
 */

suspend fun main() {

    
//    thread {
//        println("thread ==== > ${Thread.currentThread().name}")
//    }
//
//    Thread {
//        println("thread2 ==== > ${Thread.currentThread().name}")
//    }.start()
//
//    Thread {
//        //这里的run是标准库函数，和Thread无任何关系
//        run {
//            println("thread3 ==== > ${Thread.currentThread().name}")
//        }
//    }.start()

//    object : Thread() {
//
//        override fun start() {
//            super.start()
//        }
//
//        override fun isInterrupted(): Boolean {
//            return super.isInterrupted()
//        }
//
//
//    }

//    val callback: (String) -> Unit = { str ->
//        println("callback result:$str")
//    }
//
//    val result = asyncString("1")
////    println("result:$result")
//    result.also(callback)
//
//    val inputStream: InputStream? = null
//    inputStream?.use {  }

    funRun()
    println("funRun finished === > ")
    GlobalScope.launch {  }
}

suspend fun funRun() = suspendCoroutine<Int> { continuation ->
    thread {
        println("thread run === > ${Thread.currentThread().name}")
        Thread.sleep(3000)
        println("thread end === > ")
        continuation.resumeWith(Result.success(1))
    }
    println("1111")
}


fun getString() = "111"

fun asyncString(url: String): String {
    return when (val str = getString()) {
        "1" -> "2"
        "111" -> str + "222"
        else -> "33"
    }
}




