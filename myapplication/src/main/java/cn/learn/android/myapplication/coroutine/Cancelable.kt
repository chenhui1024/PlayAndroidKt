package cn.learn.android.myapplication.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2021/10/28
 * @edit
 */

@InternalCoroutinesApi
suspend fun main() {
//    delayTest()
//    getNameWithTimeout()
}

@InternalCoroutinesApi
suspend fun getNameWithTimeout() {
    var externalCoroutine: CoroutineScope? = null
    val coroutine = GlobalScope.launch {
        val equals = (externalCoroutine === this)
        println("externalCoroutine:$externalCoroutine, equals:$equals")
//        println("isInstance:${this is AbstractCoroutine<*>}")
        val name = withTimeoutOrNull(2000) {
            delay(3000)
            getName()
        }
//        println("name:$name")
    }
    externalCoroutine = coroutine as CoroutineScope
    println("coroutine is AbstactCoroutine:${coroutine is AbstractCoroutine<*>}")
    coroutine.join()
}

suspend fun getName() = suspendCoroutine<String> { continuation ->
    continuation.resume("ch")
}

suspend fun delayTest() {
    GlobalScope.launch {
        //这时候会抛出一个取消异常，如果想要不抛出异常，可以使用withTimeoutOrNull
//        val result = withTimeout(3000) {
//            10
//            delay(5000)
//        }
//        println("result:$result")

        val result = withTimeoutOrNull(3000) {
            delay(5000)
            10
        }
        println("result:$result")

    }.join()

}

