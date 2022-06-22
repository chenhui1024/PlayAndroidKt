package cn.learn.android.myapplication.coroutine

import android.os.SystemClock
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2021/10/20
 * @edit
 */

suspend fun main() {
    println("main === > ${Thread.currentThread().name}")
    val result = fun2("1,", "2")
    println("result:$result")
    println("result === > ${Thread.currentThread().name}")
}

/**
 *  挂起函数可以直接返回（同步）
 */
suspend fun func01() {
    return
}

/**
 *   挂起函数可以异步返回
 */
suspend fun fun2(a: String, b: String) = suspendCoroutine<Int> { continuation ->
//    println("continuation is SafeContinuation:${continuation is SafeContinuation}")
    thread {
        println("fun2 === > ${Thread.currentThread().name}")
        continuation.resumeWith(Result.success(10))
//        SystemClock.sleep(3000)
    }
}


