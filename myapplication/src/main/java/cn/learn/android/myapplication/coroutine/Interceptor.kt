package cn.learn.android.myapplication.coroutine

import android.os.SystemClock
import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * @description
 * @author ch
 * @date 2021/10/22
 * @edit
 */
suspend fun main() {
//    testSuspend()

}



class Info<T> {

}

class Generator<T>(
    private val block: suspend Info<T>.(T) -> Unit,
    private val param: T
) {

    suspend fun invoke() {
        Info<T>().block(param)
//        block.invoke(Info(), param)
    }

}

/**
 *   测试拦截器
 */
class LogIntecrptor : ContinuationInterceptor {

    override val key = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> = LogConfinuation(continuation)

}

class LogConfinuation<T>(private val continuation: Continuation<T>)
    : Continuation<T> by continuation {

    init {
        println("LogConfinuation create === > ")
    }

    override fun resumeWith(result: Result<T>) {
        println("before resumeWith === > $result")
        continuation.resumeWith(result)
        println("after resumeWith === > $result")
    }

}


/**
 *   测试挂起
 */
fun testSuspend() {
    suspend {
        fun1()
//        println("end === > ")
//        fun2()
    }.startCoroutine(object : Continuation<Int> {

//        override val context: CoroutineContext = EmptyCoroutineContext
        override val context: CoroutineContext = LogIntecrptor()

        override fun resumeWith(result: Result<Int>) {
            println("finished === > $result")
        }


    })
}


suspend fun fun1() = suspendCoroutine<Int> { continuation ->
    thread {
//        println("func1 continuation:$continuation")
        println("fun1 run === > ")
        Thread.sleep(3000)
        println("fun1 end === > ")
        continuation.resumeWith(Result.success(1))
    }
    //这句话会马上执行，但是函数体外的函数则不会被执行，会等待这里的返回
//    println("fun1 finished === > ")
}

suspend fun fun2() = suspendCoroutine<Int> { continuation ->
    thread {
        println("func2 continuation:$continuation")
//        println("fun2 run === > ")
        Thread.sleep(3000)
//        println("fun2 end === > ")
        continuation.resumeWith(Result.success(2))
    }
//    println("fun2 finished === > ")
}



