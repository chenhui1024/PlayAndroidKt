package com.example.playandroid_kt.rengwuxian.lesson4

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 */
class Lesson4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson4)

        //生产者
        var produce: Producer<Fruit> = FruitProducer()
        var fruit = produce.produce("香蕉")

        //消费者
        var consumer: Consumer<Fruit> = FruitConsumer()
        consumer.consume(Apple())
        consumer.consume(Banana())

        //多继承的时候，可以使用where
//        val c = C<E>()
        val c: C<E> = C<E>()
        c.print(E())

        var listener = CallBack()
        listener.listener = object : ICallBackListener {
            override fun onSuccess() {

            }

            override fun onError(code: String, errorDesc: String) {

            }
        }

//        findViewById<Button>(R.id.btn_test).setOnTouchListener(object : View.OnTouchListener {
//
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                return false
//            }
//        })
//        findViewById<Button>(R.id.btn_test).setOnTouchListener { view, event ->
//            false
//        }
    }

    interface A {
        fun getName() : String
    }

    interface B {
        fun getAge() : Int
    }

    class E : A, B {
        override fun getName(): String {
            return "i am class E"
        }

        override fun getAge(): Int {
            return 100
        }
    }

    open class D {
        fun printTime() {
            val now = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date())
            println("now:$now")
        }
    }

    class C<T> : D() where T : A, T : B {

        fun print(t: T) {
            printTime()
            println("t name:${t.getName()},t age:${t.getAge()}")
        }

    }

    class CallBack {

        var listener: ICallBackListener? = null

        fun result(result: Boolean) {
            if (result) {
                listener?.onSuccess()
            } else {
                listener?.onError("XX", "交易失败")
            }
        }

    }

}