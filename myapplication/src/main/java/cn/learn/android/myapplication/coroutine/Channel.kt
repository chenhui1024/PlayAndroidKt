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
    val channel = Channel<Int>().broadcast(3)//3表示缓冲区的大小
    val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)
    val producer = GlobalScope.launch {
        List(3) {
            //这个延迟100是必要的，不然订阅者可能收不到发送的“0”
            //因为broadcastChannel在发送数据时，如果没有订阅者，那么这条数据就会被丢弃
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
//            println("消费一个：$element")
//        }
//    }

    GlobalScope.launch {
        val receiveChannel: ReceiveChannel<Int> = GlobalScope.produce {
            repeat(10) {
                delay(1000)
                send(it)
                println("生产一个：$it")
            }
        }

        launch {
            println("name:${Thread.currentThread().name}")
            while (true) {
                val element = receiveChannel.receive()
                println("消费一个：$element")
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
                println("生产一个：$i")
                channel.send(i++)
            }
        }

        val consumer = launch {
            val iterator = channel.iterator()
            while (iterator.hasNext()) {
                delay(2100)
                val element = iterator.next()
                println("消费一个：$element")
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
    //所以，验证了channel就是一个队列
    //send和receive都是挂起函数
//    val channel = Channel<Int>(Channel.CONFLATED)
    val channel = Channel<Int>()
    GlobalScope.launch {
        val producer = launch {
            var i = 0
            while (true) {
                delay(1000)
                println("生产一个：$i")
                channel.send(i++)
            }
        }

        val consumer = launch {
            while (true) {
                delay(2100)
                val element = channel.receive()
                println("消费一个：$element")
            }
        }

        producer.join()
        println("producer join == > ")
        consumer.join()
        println("consumer join == > ")
    }.join()

}
