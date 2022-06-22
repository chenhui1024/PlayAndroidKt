package cn.learn.android.myapplication.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 * @description
 * @author ch
 * @date 2021/10/20
 * @edit
 */

suspend fun main() {
//    func()
//    val interf = object : AInt<String> {
//
//    }

//    val name = "111"
//    name.onClick { internalName ->
//        println("intername:$internalName")
//    }

    var internalJob: Job? = null
    val job = GlobalScope.launch {
        internalJob = coroutineContext[Job]
        println("context:$coroutineContext")
        throw IllegalArgumentException("11111")
    }
    job.join()
    println("e:${job === internalJob}")
}

fun String.onClick(context: CoroutineContext = Dispatchers.IO,
                           block: suspend CoroutineScope.(String) -> Unit) {
    GlobalScope.launch(context) {
        block(this@onClick)
    }
}

suspend fun joinSuspend() = suspendCancellableCoroutine<Unit> { continuation ->
}

class MyInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*> = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("Not yet implemented")
    }

}

interface MyInt

class CoroutineExceptionHandler(val onError: (Throwable) -> Unit) : AbstractCoroutineContextElement(Key) {

    //这里的“：”表示继承，这里的Key也可以不写，companion也可以不写。相当于新建了这么一个类，并创建了这么一个实例
    companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>

}
//
//interface AInt<E : CharSequence>
//
//class Ahandler {
//
//}

class CommonHanlder(val onSuccess: (Int) -> Unit) : AbstractCoroutineContextElement(Key) {

    //    companion object Key: CoroutineContext.Key<CommonHanlder>
//    companion object : CoroutineContext.Key<CommonHanlder>
    object Key : CoroutineContext.Key<CommonHanlder>

//    companion object {
//        val Key: CoroutineContext.Key<CommonHanlder> = object : CoroutineContext.Key<CommonHanlder> {}
//    }

}

fun func() {

    val errorContext = CoroutineExceptionHandler {
        println("error happened === > ${it.message}")
    }

    val realContext = errorContext + CommonHanlder {
        println("result:$it")
    }

    //替换前一个
    val testContext = realContext + CommonHanlder {
        println("result2:$it")
    }

    suspend {
        println("start === > ${coroutineContext[CoroutineName]}")
//        throw RuntimeException("set an exception!")
        1
    }.startCoroutine(object : Continuation<Int> {

        override val context: CoroutineContext = testContext

        override fun resumeWith(result: Result<Int>) {
            println("key:${CommonHanlder.Key}")
            result.onSuccess {
                context[CommonHanlder.Key]?.onSuccess?.invoke(result.getOrNull()!!)
            }
            result.onFailure {
                result.exceptionOrNull()?.let {
                    context[CoroutineExceptionHandler]?.onError?.invoke(it)
                }
            }
        }

    })
}


