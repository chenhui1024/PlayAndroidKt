package cn.learn.android.myapplication.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2021/11/2
 * @edit
 */
suspend fun main() {

    val exceptionHanlder = AppExceptionHandler {
        println("throwable is :${it.message}")
    }

    GlobalScope.launch(exceptionHanlder) {
        println("1 === > ")
        throw Exception("exception happened === > ")
    }.join()
}

class AppExceptionHandler(val onError: (Throwable) -> Unit) : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        onError.invoke(exception)
    }

}
