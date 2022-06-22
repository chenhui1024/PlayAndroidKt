package cn.learn.android.myapplication.coroutine

import android.os.SystemClock
import android.view.View
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * @description
 * @author ch
 * @date 2021/10/19
 * @edit
 */

fun <T> ((String) -> T).testOuput() {
}

suspend fun main() {
//    val f = { name: String ->
//        println("name is $name")
//        1
//    }
//    f.testOuput()

    //创建协程的第一种方式
    createCoroutine()

//    创建协程的第二种方式
//    createCoroutine2()

    //第三种
//    launchCoroutine(ProducerScope<Int>()) {
//        produce(11)
//        12
//    }


//    launch(RestrictProducerScope()) {
////        delay(1) //报错，因为限制了调用外部挂起函数
//        produce()
//    }
}

suspend fun notSuspend() = suspendCoroutine<Int> { continuation ->
    continuation.resume(100)
    GlobalScope.launch {
    }
}

suspend fun <R, T> launch(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("result:$result")
        }
    })
}

@RestrictsSuspension
class RestrictProducerScope {
    suspend fun produce() {
    }
}

/**
 *   创建协程的第一种方式
 */
fun createCoroutine() {

    val receiver: Continuation<Int> = object : Continuation<Int> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("coroutine end:${result.getOrNull()}")
        }

    }
    val continuation = suspend {
        println("in coroutine.")
        5
    }.createCoroutine(receiver)

    println("result:${continuation === receiver}")
    continuation.resumeWith(Result.success(Unit))
//    continuation.resumeWith(Result.failure(RuntimeException("11111")))
}

/**
 *   创建协程的第二种方式
 *
 *   我们的协程体本身就是一个Continuation实例，正因如此挂起函数才能在协程体内运行。在协程内部挂起函数的调用处被称为挂起点
 *   挂起点如果出现异步调用，那么当前协程就被挂起，直到对应的Continuation的resume函数被调用才会恢复执行。
 */
fun createCoroutine2() {

    suspend {
        println("in coroutine.")
//        SystemClock.sleep(3000)
        println("thread name:${Thread.currentThread().name}")
        6
    }.startCoroutine(object : Continuation<Int> {

        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("coroutine end:${result.getOrNull()}")
        }

    })
}

/**
 *   创建协程，第三种方式。这里加入了一个接收者，让作用域再R内
 */
fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("end:$result")
        }
    })
}

class ProducerScope<T> {

    suspend fun produce(value: T) {
        println("produce:$value")
    }
}




