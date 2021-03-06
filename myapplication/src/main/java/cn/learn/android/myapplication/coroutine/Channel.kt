package cn.learn.android.myapplication.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.suspendCoroutine

/**
 * @description
 * @author ch
 * @date 2021/10/28
 * @edit
 */
suspend fun main() {
//    testSendReceive()

//    testIterator()

//    testEasyCreate()

//    testBroadcastChannel()

    testProduce()

//    getReceiveChannel()
//    getSendChannel()
}

suspend fun getSendChannel() {
    val sendChannel = GlobalScope.actor<Int> {
        while (true) {
            val value = receive()
            println("value:$value")
        }
    }

    repeat(10) {
        sendChannel.send(it)
        delay(1000)
    }
    sendChannel.close()

    println("finished!")
}

suspend fun getReceiveChannel() {
    val receiveChannel = GlobalScope.produce {
        repeat(10) {
            send(it)
//            delay(1000)
        }
    }

    GlobalScope.launch {
        for (value in receiveChannel) {
            println("receive1:$value")
        }
    }

    GlobalScope.launch {
        for (value in receiveChannel) {
            println("receive2:$value")
        }
    }.join()

//    for (value in receiveChannel) {
//        println("receive:$value")
//    }
}

suspend fun testProduce() {
    val channel = GlobalScope.produce(Dispatchers.Unconfined) {
        println("A")
        send(1)
        println("B")
        send(2)
        println("done")
    }

//    println("for")
//    for (item in channel) {
//        println("Got $item")
//    }

//    GlobalScope.launch {
//
//        println("start")
//        withContext(Dispatchers.IO) {
//            println("sleep === > ")
//            Thread.sleep(3000)
//        }
//        println("end")
//
//    }.join()
//
//
//    val r = listOf("1", "2", "3").map {
//        it to 1
//    }.toMap(ConcurrentHashMap<String, Int>())
}

suspend fun testBroadcastChannel() {
//    val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)
    val channel = Channel<Int>().broadcast(3)//3????????????????????????
    val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)
    val producer = GlobalScope.launch {
        List(3) {
            //????????????100?????????????????????????????????????????????????????????0???
            //??????broadcastChannel??????????????????????????????????????????????????????????????????????????????
            delay(200)
            broadcastChannel.send(it)
        }
        broadcastChannel.close()
    }

    List(3) {
        GlobalScope.launch {
            val receiveChannel = broadcastChannel.openSubscription()
            for (i in receiveChannel) {
                println("[$it] received: $i")
            }
        }
    }.joinAll()
}

suspend fun testEasyCreate() {
//    val sendChannel: SendChannel<Int> = GlobalScope.actor {
//        while (true) {
//            delay(2100)
//            val element = receive()
//            println("???????????????$element")
//        }
//    }

    GlobalScope.launch {
        val receiveChannel: ReceiveChannel<Int> = GlobalScope.produce {
            repeat(10) {
                delay(1000)
                send(it)
                println("???????????????$it")
            }
        }

        launch {
            println("name:${Thread.currentThread().name}")
            while (true) {
                val element = receiveChannel.receive()
                println("???????????????$element")
            }
        }

    }.join()

}

suspend fun testIterator() {
    val channel = Channel<Int>()
    GlobalScope.launch {
        val producer = launch {
            var i = 0
            while (true) {
                delay(1000)
                println("???????????????$i")
                channel.send(i++)
            }
        }

        val consumer = launch {
            val iterator = channel.iterator()
            while (iterator.hasNext()) {
                delay(2100)
                val element = iterator.next()
                println("???????????????$element")
            }
        }
//        println("statrt to join == > ")
//        producer.join()
//        println("producer join == > ")
//        consumer.join()
//        println("consumer join == > ")
    }.join()
}

suspend fun testSendReceive() {
    //??????????????????channel??????????????????
    //send???receive??????????????????
//    val channel = Channel<Int>(Channel.CONFLATED)
    val channel = Channel<Int>()
    GlobalScope.launch {
        val producer = launch {
            var i = 0
            while (true) {
                delay(1000)
                println("???????????????$i")
                channel.send(i++)
            }
        }

        val consumer = launch {
            while (true) {
                delay(2100)
                val element = channel.receive()
                println("???????????????$element")
            }
        }

        producer.join()
        println("producer join == > ")
        consumer.join()
        println("consumer join == > ")
    }.join()

}
