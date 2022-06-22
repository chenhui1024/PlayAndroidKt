package cn.learn.android.myapplication

import java.lang.StringBuilder

/**
 * @description
 * @author ch
 * @date 2021/8/31
 * @edit
 */
class TestSingleInstance {

    companion object {
        init {
            println("TestSingleInstance init22 === > ")
        }
    }

    init {
        println("TestSingleInstance init === > ")
    }

    fun print() {
        println("TestSingleInstance print invoke...")
    }


}

object InstanceEx {

    init {
        println("InstanceEx init === > ")
    }

    fun print() {
        println("print invoke...")
    }

}


fun main() {
    /**
     * init 代码块，如果是放在类中，那每次创建对象都会调用。如果是放在companion object中，则相当于static{}对象，只在加载的时候创建一次
     */
//    InstanceEx.print()
//    InstanceEx.print()
//    TestSingleInstance().print()
//    TestSingleInstance().print()

    println(TestSingleInstance::class.java)
}

fun String.getStrings(): String = if (this == "111") "111" else "222"

