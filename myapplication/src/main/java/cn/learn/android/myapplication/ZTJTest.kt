package cn.learn.android.myapplication

import cn.learn.android.myapplication.coroutine.CoroutineExceptionHandler
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2022/1/15
 * @edit
 */

suspend fun main() {
    val result = getResult()
    println("result:$result")
}

suspend fun getResult(): Double {
    val result = getUserInfo()
    val r = getFriendList(result)
    return getFeedList(r)
}

suspend fun getUserInfo() = suspendCoroutine<Int> { continuation ->
    thread {
        continuation.resume(10)
    }
}

suspend fun getFriendList(num: Int) = suspendCoroutine<String> { continuation ->
    thread {
        continuation.resume(num.toString())
    }
}

suspend fun getFeedList(num: String) = suspendCoroutine<Double> { continuation ->
    thread {
        continuation.resume(num.toDouble())
    }
}



