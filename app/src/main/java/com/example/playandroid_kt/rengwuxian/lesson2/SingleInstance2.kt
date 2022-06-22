package com.example.playandroid_kt.rengwuxian.lesson2

import android.util.Log

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit：companion object里面的就是静态的，相当于java里面的static
 */
class SingleInstance2 {

    val name = "cc"

    object TT {
        fun tt() {
            Log.d("chenhui", "111")
        }
    }

    //用 object 修饰的对象中的变量和函数都是静态的，但有时候，我们只想让类中的一部分函数和变量是静态的.
    // 这里面的方法和属性就是静态的
    //一个类中最多只可以有一个伴生对象，但可以有多个嵌套对象。就像皇帝后宫佳丽三千，但皇后只有一个。
    companion object Test {//这里的Test也可以去掉.当有 companion 修饰时，对象的名字也可以省略掉

        const val TAG = "SingleInstance2"

        init {
            //Java 中的静态变量和方法，在 Kotlin 中都放在了 companion object 中。
            // 因此 Java 中的静态初始化在 Kotlin 中自然也是放在 companion object 中的，
            // 像类的初始化代码一样，由 init 和一对大括号表示：
            Log.d(TAG, "SingleInstance2 Test init")
        }

        val age = 99

        fun test() {
            Log.d(TAG, "test print === > ")
        }

//        class A {
//
//            companion object {
//
//            }
//
//        }

    }

    //kotlin中全部的内部类都是默认静态的，不能持有外部类的引用，如果需要访问外部类的属性或者方法，需要上架Inner关键字,使其变成非静态类
    class Inner {
         private val number = 1
    }


    fun method() {
        var inner: Inner = Inner()

        //不可见
//        inner.number
    }

}