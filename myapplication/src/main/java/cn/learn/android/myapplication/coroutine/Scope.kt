package cn.learn.android.myapplication.coroutine

import android.os.Looper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * @description
 * @author ch
 * @date 2021/10/27
 * @edit
 */
suspend fun main() {
    //1.
//    scopeParent()

    //2.
//    testGlobalScope()

    //3.
//    testDelayCancel()

    //4.作用域的特殊用法
    val show: InterfaceA = InterfaceAImpl()
    invoke {
        show {
            println("block running === > ")
        }
    }
}

suspend fun testDelayCancel() {
    GlobalScope.launch {
//        val job = launch {
//            listOf(1, 2, 3, 4).forEach {
//                println("num:$it")
//                //检查协程是否被取消，如果被取消则抛出一个取消异常
//                yield()
//                delay(1000L * it)
//            }
//        }
//        println("start to cancel")
//        delay(3000)
//        job.cancel(null)//这里的取消可能是被yield检测到，也有可能是被delay检测到。
        // 因为delay也可以响应取消。所以采用下面这种做法
//        job.cancelAndJoin()

        val job = launch {
            listOf(1, 2, 3, 4).forEach {
                println("num:$it")
                yield()
                //这样的话，delay就不会响应取消了
                withContext(NonCancellable) {
                    delay(1000L * it)
                }
            }
        }
        println("start to cancel")
        delay(3000)
        job.cancelAndJoin()

    }.join()

}

suspend fun testGlobalScope() {
    GlobalScope.launch(Dispatchers.IO) {
    }.join()
    println("finished === > ")
}

class SuccessHanlder(val onSuccess: (String) -> Unit) : AbstractCoroutineContextElement(SuccessHanlder) {

    companion object : CoroutineContext.Key<SuccessHanlder>

}

class ExceptionHanlder(private val onError: (Throwable) -> Unit) : AbstractCoroutineContextElement(ExceptionHanlder) {

    companion object Key : CoroutineContext.Key<ExceptionHanlder>

    fun onErrorHappened(throwable: Throwable?) {
        throwable?.let {
            onError.invoke(throwable)
        }
    }

}

fun scopeParent() {

    val successContext = SuccessHanlder {
        println("success handler String === > $it")
    }

    val resultContext = successContext + ExceptionHanlder {
        println("error happened === > ${it.message}")
    }

    suspend {
        println("parent fun start === > ")
        scopeFunChild1()
        println("scopeFunChild1 fun end === > ")
        val result = scopeFunChild2()
        println("parent fun end === > ")
        result
    }.startCoroutine(object : Continuation<String> {
        override val context: CoroutineContext = resultContext

        override fun resumeWith(result: Result<String>) {
            println("resumeWith === > $result")
            result.onFailure {
                println("onFailure === > ")
                context[ExceptionHanlder]?.onErrorHappened(it)
            }
            result.onSuccess {
                println("onSuccess === > ")
                context[SuccessHanlder]?.onSuccess?.invoke(it)
            }
        }

    })

}

suspend fun scopeFunChild1() = suspendCoroutine<Int> { continuation ->
    thread {
        Thread.sleep(3000)
        continuation.resume(1)
    }
}

suspend fun scopeFunChild2() = suspendCoroutine<String> { continuation ->
    continuation.resume("2")
}

/**
 *  作用域的特殊用发
 */
interface InterfaceA {
    fun show(parent: InterfaceB, block: () -> Unit)
}

class InterfaceAImpl : InterfaceA {
    override fun show(parent: InterfaceB, block: () -> Unit) {
        println("show === > ")
        block()
    }
}

fun invoke(block: InterfaceB.() -> Unit) {
    val impl = InterfaceBImpl()
    block(impl)
}

interface InterfaceB {
    operator fun InterfaceA.invoke(block: () -> Unit)
}

class InterfaceBImpl : InterfaceB {
    override fun InterfaceA.invoke(block: () -> Unit) {
        show(this@InterfaceBImpl, block)
    }
}





