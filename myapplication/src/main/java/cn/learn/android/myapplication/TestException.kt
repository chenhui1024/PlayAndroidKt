package cn.learn.android.myapplication

import android.os.IInterface
import android.os.SystemClock
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * @description
 * @author ch
 * @date 2022/1/19
 * @edit
 */

suspend fun main() {
    val context = CoroutineExceptionHandler { _, throwable ->
        println("error happened:${throwable.message}")
    }

    GlobalScope.launch(context) {
        normalRun1()
        val internalContext = CoroutineExceptionHandler { _, throwable ->
            println("internal error happened:${throwable.message}")
        }

        launch(SupervisorJob()) {
            launch(internalContext) {
                errorRun()
            }
        }


//        launch(SupervisorJob() + internalContext) {
//            launch {
//                launch {
//                    errorRun()
//                }
//            }
//        }

        //以下两个方法都可以
//        launch(internalContext + SupervisorJob(coroutineContext[Job])) {
//                errorRun()
//        }
//        launch(SupervisorJob() + internalContext) {
//            errorRun()
//        }
//        CoroutineScope(SupervisorJob()).launch(internalContext) {
//            errorRun()
//        }
//        CoroutineScope(SupervisorJob() + internalContext).launch {
//            errorRun()
//        }
    }.join()
//    CoroutineScope(SupervisorJob() + Dispatchers.Default).launch(context) {
//        errorRun()
//    }
    Thread.sleep(1000)
}

suspend fun normalRun1() = suspendCoroutine<Int> {
    thread {
        it.resumeWith(Result.success(1))
    }
}

suspend fun errorRun() = suspendCoroutine<String> {
    thread {
        it.resumeWith(Result.failure(IllegalArgumentException("error argument")))
    }
}




