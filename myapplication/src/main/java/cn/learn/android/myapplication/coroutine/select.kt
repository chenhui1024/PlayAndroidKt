package cn.learn.android.myapplication.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

/**
 * @description
 * @author ch
 * @date 2021/11/7
 * @edit
 */

suspend fun main() {
//    GlobalScope.launch {
//        val localDeferred = getUserFromLocal()
//        val netDeferred = getUserFromNet()
//
//        val user = select<Response<User?>> {
//            localDeferred.onAwait {
//                Response(it, true)
//            }
//            netDeferred.onAwait {
//                Response(it, false)
//            }
//        }
//        println("user:${user.value}")
//    }.join()
}

fun CoroutineScope.getUserFromNet() = async(Dispatchers.IO) {
    Thread.sleep(3000)
    User("fanxian", 28)
}

fun CoroutineScope.getUserFromLocal() = async(Dispatchers.IO) {
    Thread.sleep(4000)
    User("chenhui", 29)
}

class Response<T>(val value: T, fromLocal: Boolean = false)

data class User(val name: String, val age: Int)







