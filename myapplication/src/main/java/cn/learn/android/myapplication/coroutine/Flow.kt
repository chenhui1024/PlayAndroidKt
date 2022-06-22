package cn.learn.android.myapplication.coroutine

import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

/**
 * @description
 * @author ch
 * @date 2021/11/4
 * @edit
 */
suspend fun main() {

//    secondVersion()

//    handleException()

//    cancelFlow()

//    cannotDispatch()
//    canDispatch()

//    testBeiYa()

//    map()

//    listOf(1, 2, 3).asFlow().collect {
//        println("it:$it")
//    }
//    setOf(1, 2, 3).asFlow().collect {
//        println("it:$it")
//    }
//    flowOf(1, 2, 3).collect {
//        println("it:$it")
//    }

    withContext(Dispatchers.Default) {
        flow {
            (1..3).forEach {
                println("${Thread.currentThread().name},emit:$it")
                emit(it)
                delay(100)
            }
        }.onEach{
            println("${Thread.currentThread().name} 重要的线程")
        }.flowOn(Dispatchers.IO).onEach {
            println("${Thread.currentThread().name},onEach:$it")
        }.collect {
            println("${Thread.currentThread().name},collect:$it")
        }
    }
}

suspend fun map() {
//    flow {
//        List(10) {
//            emit(it)
//        }
//    }.map {
//        it * 2
//    }.collect {
//        println("it:$it")
//    }

//    flow {
//        List(10) {
//            emit(it)
//        }
//    }.map {
//        flow {
//            List(it) {
//                emit(it)
//            }
//        }
//    }.collect {
//        it.collect {
//            println("it:$it")
//        }
//    }

//    flow {
//        List(10) {
//            emit(it)
//        }
//    }.map {
//        flow {
//            List(it) {
//                emit(it)
//            }
//        }
//    }.flattenConcat().collect {
//        println("it:$it")
//    }

//    flow {
//        List(10) {
//            emit(it)
//        }
//    }.map {
//        flow {
//            List(it) {
//                emit(it)
//            }
//        }
//    }.flattenMerge().collect {
//        println("it:$it")
//    }

}

suspend fun testBeiYa() {
//    flow {
//        List(10) {
//            emit(it)
//        }
//    }.conflate().collect {
//        println("collecting $it")
//        delay(100)
//        println("$it collected")
//    }

    flow {
        List(10) {
            emit(it)
        }
    }.collectLatest {
        println("collecting $it")
        delay(100)
        println("$it collected")
    }

}

/**
 *   运行报错！！！
 */
suspend fun cannotDispatch() {
    flow {
        emit(1)
        withContext(Dispatchers.IO) {
          emit(2)
        }
    }.collect {
        println("receive:$it")
    }

}

suspend fun canDispatch() {
    channelFlow {
        send(1)
        withContext(Dispatchers.IO) {
            send(2)
        }
    }.collect {
        println("receive:$it")
    }
}

suspend fun cancelFlow() {
    val job = GlobalScope.launch {
        val flow = flow {
            (1..3).forEach {
                delay(1000)
                emit(it)
            }
        }

        flow.collect { println("receive:$it") }
    }
    delay(2100)
    job.cancelAndJoin()
}

fun firstVersion() {
    val sequence = sequence {
        (1..3).forEach {
            //这里面是不能调用其他挂起函数的，只能调用它内部允许的挂起函数
            println("it:$it")
            yield(it)
        }
    }
    for (value in sequence) {
        println("value:$value")
    }
}

suspend fun secondVersion() {
    val intFlow = flow {
        (1..3).forEach {
            emit(it)
            delay(100) //Flow是可以支持调用其他的挂起函数的
        }
    }
    GlobalScope.launch {
        intFlow.flowOn(Dispatchers.IO)
            .collect {
                println("get:$it")
            }
    }.join()
}

suspend fun handleException() {
    val flowE = flow {
        emit(1)
        emit(2)
//        throw RuntimeException("exception happened !")
    }.catch { cause: Throwable ->
        println("cause:${cause.message}")
        emit(2)
    }

    flowE.flowOn(Dispatchers.IO)
        .collect {
            println("get:$it")
        }
}


