package com.example.playandroid_kt.rengwuxian.lesson2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.lesson1.Apple
import java.util.*

/**
 * @description
 * @author ch
 * @date 2020/6/29
 * @edit
 *
 * 讲完了数据集合，再看看 Kotlin 中的可见性修饰符，Kotlin 中有四种可见性修饰符：
 *
    public ：公开，可见性最大，哪里都可以引用。
    private：私有，可见性最小，根据声明位置不同可分为类中可见和文件中可见。
    protected：保护，相当于 private + 子类可见。
    internal：内部，仅对 module 内可见。

    在 Android 的官方 sdk 中，有一些方法只想对 sdk 内可见，
    不想开放给用户使用（因为这些方法不太稳定，在后续版本中很有可能会修改或删掉）。
    为了实现这个特性，会在方法的注释中添加一个 Javadoc 方法 @hide，用来限制客户端访问：

    但这种限制不太严格，可以通过反射访问到限制的方法。针对这个情况，Kotlin 引进了一个更为严格的可见性修饰符：
    internal：internal 表示修饰的类、函数仅对 module 内可见，这里的 module 具体指的是一组共同编译的 kotlin 文件。

 *
 *
 *
 */
class Lesson2Activity: AppCompatActivity {

    companion object {
        private const val TAG = "Lesson2Activity"
        //这句话是不允许的，因为const只允许是基本数据类型，或者是String
//        private const val Apple = Apple()
    }

    constructor()

    private lateinit var btnTest: Button

    /*  都是不可修改、不能添加、删除元素的 */
    var strList: List<String> = listOf("11", "22", "33")
    val strSet: Set<String> = setOf("11", "22", "33")
    val strMap: Map<Int, String> = mapOf(1 to "2", 3 to "4")

    /* 可添加，可修改 */
    val mutableList: MutableList<String> = mutableListOf("11", "22", "33")
    val mutableMap = mutableMapOf<Int, String>(1 to "2", 3 to "4")

    /*  Any就是java中的Object */
    val value: Any = "2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson2)

        SingleInstance.print()

        SingleInstance2.Test.test()

        Log.d("chenhui", "topLevel:" + getDescprition())
        println("this is a print test")
        Log.d(TAG, "tag test")

        printMap()

        println("math.max:" + max(10, 11))

        printList()

        println(InterfaceImpl.getName())

        println(InterfaceImpl.getClsName())

        createIntervalCls()

        getIntervalName()

        var priCls = PrivateCls.getInstance()
    }

    private fun createIntervalCls() {
        //和 Java 创建匿名类的方式很相似，只不过把 new 换成了 object:：
        //Kotlin 中 object: 也可以用来创建匿名类的对象
        //这里的 new  和 object: 修饰的都是接口或者抽象类。
        btnTest = findViewById(R.id.btn_print)
        val listener = object : View.OnClickListener {

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }

        }
        btnTest.setOnClickListener(listener)
        btnTest.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun printList() {
        mutableList.add("444")
        for (str in mutableList) {
            Log.d(TAG, "str:$str")
        }
    }

    private fun printMap() {
        for (entry in strMap) {
            Log.d(TAG, "key:${entry.key},value:${entry.value}")
        }

//        strMap.forEach { t, u ->
//            Log.d(TAG, "t:$t,u:$u")
//        }

        for ((k, v) in strMap) {
            Log.d(TAG, "k:$k,v:$v")
        }

        Log.d(TAG, "map[0]:" + strMap.get(1))
        Log.d(TAG, "map[1]:" + strMap[3])

        mutableMap.put(5, "6")
        mutableMap[7] = "8"


        //可以通过该接口进行转换
        //返回的是一个新建的集合，原有的集合还是不可变的，所以只能对函数返回的集合修改
//        var toMutableMap = strMap.toMutableMap()
    }


    fun max(a: Int, b: Int): Int = Math.max(a, b)

}