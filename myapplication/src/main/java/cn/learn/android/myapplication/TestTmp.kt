package cn.learn.android.myapplication

import android.os.Build
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

/**
 * @description
 * @author ch
 * @date 2021/9/10
 * @edit
 */

class AAA(val age: Int) {

    val name: String
        get() {
            println("get() === > ")
            return if (age > 10) "111" else "222"
        }

}

class InternalUser {

    val name: String

    constructor(name: String) {
        this.name = name
    }

    constructor(id: Int) {
        this.name = id.toString()
    }

}

class InstanceWrapper {

    companion object Internal {
        fun test() {
        }
    }

}

class SingletonImpl private constructor() {

    companion object {
        private var INSTANCE: SingletonImpl? = null

        @Synchronized
        fun getInstance(): SingletonImpl {
            if (INSTANCE == null) {
                INSTANCE = SingletonImpl()
            }
            return INSTANCE!!
        }
    }

}

fun getAge(women: Women): Int {
    return women.age
}

fun printAge(women: Women, getAge: (Women) -> Int): Int {
    return getAge(women)
}

data class SmallPoint(val x: Int, val y: Int) {

    operator fun plus(smallPoint: SmallPoint): SmallPoint {
        println("plus")
        return SmallPoint(x + smallPoint.x, y + smallPoint.y)
    }

}

class DelegateValue {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = 1

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
    }
}

class ByCls {
    //    val age: Int by DelegateValue()
    private val attributeSet = hashMapOf<String, String>()
    fun setAttribute(name: String, value: String) {
        attributeSet[name] = value
    }
    val name: String by attributeSet
}

fun test(method: (Int) -> Unit) {
    println("before === > ")
    method(10)
    println("after === >")
}
fun invokeTest() {
//    StringBuilder().apply sb@{
//        listOf(1, 2, 3).apply {
//            this@sb.append(this.toString())
//        }
//    }
//
//
//    test label@{
//        if (it > 5)
//            return@label
//    }

    val list = listOf(1, 2, 3)
    list.forEach(fun (num) {
        if (num >= 2) {
            return
        }
        println("num:$num")
    })
    println("finished!")

}

class NumCls(val num: Int) {
    fun testF(message: String) = 0
}

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
//    val num = NumCls(10)
//    val clsFunc = NumCls::testF
//    val r = clsFunc.invoke(num, "111")
//    val result = clsFunc.call(num, "111")



//    val byCls = ByCls()
//    val javaCls = byCls::class
//    javaCls.members.forEach {
//        println("call:${it.name}")
//    }
//    javaCls.memberProperties.forEach {
//        println("name:${it.name}")
//    }

//    invokeTest()
//    val women = Women(10)
//    val getAge = women::age
//    println("getAge:${getAge()}")
//    val getAgeEx = Women::age
//    printAge(women, ::getAge)

//    val btn: Button? = null
//    var count: Int = 0
//    val listener = object : View.OnClickListener, Focusable {
//        override fun onClick(v: View?) {
//            count ++
//        }
//
//        override fun show() {
//
//        }
//    }
//    btn?.setOnClickListener(object : View.OnClickListener {
//        override fun onClick(v: View?) {
//
//        }
//
//    })

//    var tmp = ClsA()
//    tmp.let {
//        println("1111")
//    }
//    tmp.log()
//    tmp.log()
//    tmp = ClsA()
//    println("clsA:${ClsA.javaClass}")
//    println("clsA:${tmp.javaClass}")
//    println("clsA:${ClsA::class.java}")
//    println("clsA:${tmp::class.java}")

//    println("${ClsA.javaClass == ClsA::class.java}")

//    println("${ClsA::class}")
//    println("${tmp.javaClass.kotlin}")
//    println("${tmp.javaClass.kotlin == ClsA::class}")

//    val a: ClsA = ClsA()
//    val b: ClsA = ClsA()
//    println("${a == b}")
//    println("${a === b}")

//    val clsA = newInstance<ClsA>()
//    clsA.log()
//    println("${clsA == tmp}")
//    println("${clsA === tmp}")
//
//    val a = 10
//    a.coerceIn(0, 100)
//    println("a ${a.hashCode()}")

//    val any: Any = 10
//    println("any ${any.hashCode()}")

//    val list = ArrayList<String?>()
//    list.add("11")
//    list.add(null)
//    list.add("222")
//    list.forEach {
//        println("list:$it")
//    }
//    list.filterNotNull().forEach {
//        println("list:$it")
//    }

//    val  list2 = mutableListOf("1", "2")
//    repeat(10) {
//        println("repeat:$it")
//    }


//    val list = listOf("a", "b", "c")
//    for (index in list.indices) {
//        println("index:$index, element:${list[index]}")
//    }
//    for ((index, element) in list.withIndex()) {
//        println("index:$index,element:$element")
//    }
//    for (s in list) {
//        println("s:$s")
//    }
//    UtilsTest.modifyList(list)
//    println("modify === > ")
//    for (s in list) {
//        println("s:$s")
//    }

//    val a = AAA(11)
//    println("age is ${a.name}")
//    println("age is ${a.name}")


//    testWhen(11.0)

//    for (i in 10 downTo 1 step 1) {
//        println("i:$i")
//    }

//    val c = 'A'
//    val i = c.toInt()
//    println("c:$i")
//    val r = Integer.toBinaryString(i)
//    println("r:$r")
//    println("r:${Integer.toHexString(i)}")

//    testMapFor()
//    val r = testReader(BufferedReader(StringReader("acb")))
//    println("result:$r")
//    val r = try {
//        UtilsTest.test(BufferedReader(StringReader("abc")))
//    } catch (e: Exception) {
////        null
//        1
//    }
//    println("result:$r")


//    val list = listOf("1", "2")
//    val item = list.findLast {
//        "1" >= it
//    }
//    println("item:$item")

//    println("time:${SimpleDateFormat.getDateTimeInstance().format(Date())}")
//    var r = SimpleDateFormat("yyyy/MMM/dd HH:mm:ss", Locale.US).format(Date())
//    println("ch time:$r")


//    val dateStr = "2022/11/11 11:30:51"
//    //如果如下没有加上Locale.US，parse就会报错了
//    val date = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA).parse(dateStr)
//    r = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date)
//    println("r:$r")

//


//    r = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(Date())
//    println("us time:$r")

//    val calendar = Calendar.getInstance()
//    calendar.set(2021, 11, 30, 12, 12, 12)
//    r = SimpleDateFormat("yyyyMMdd HHmmss").format(calendar.time)
//    println("time:$r")
//
//    calendar.time = Date()
//    r = SimpleDateFormat("yyyyMMdd HHmmss").format(calendar.time)
//    println("time:$r")
//    println("time:${calendar.get(Calendar.YEAR)}, ${calendar.get(Calendar.MONTH)}")

//    println("time:${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))}")

//    val zoneTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"))
//    val newZoneTime = zoneTime.withZoneSameInstant(ZoneId.of("America/New_York"))
//    println("zoneTime:$zoneTime")
//    println("newZoneTime:$newZoneTime")

    SimpleDateFormat()

}

class TmpCls {
    //私有对象和保护对象不允许被访问
    val stringBuilder: StringBuilder = StringBuilder()
}

fun TmpCls.append(string: String): Unit {
    stringBuilder.append(string)
}

fun TmpCls.getStrings(): String = stringBuilder.toString()

fun testReader(reader: BufferedReader): Int {
    val result = reader.readLine()
    return Integer.parseInt(result)
}

fun testMapFor() {
    val map = mapOf(1 to "2", 3 to "4")
    for ((key, value) in map) {
        println("key:${key}, value:${value}")
    }
    for (entry in map) {
        println("key:${entry.key}, value:${entry.value}")
    }
}

fun testListFor() {
    val list = listOf("a", "b", "c")
    for (index in list.indices) {
        println("index:$index, element:${list[index]}")
    }
    for ((index, element) in list.withIndex()) {
        println("index:$index,element:$element")
    }
    for (s in list) {
        println("s:$s")
    }
}

fun testWhen(any: Any) {
    when (any) {
        is String -> println("is String")
        is Int, is Float -> println("is not String")
        is Boolean, Double -> println("is not int or float")
    }
}

fun testWhen3(flag: Int): String =
    when (flag) {
        1 -> "1"
        2 -> "2"
        else -> "3"
    }

/**
 *   不带表达式的情况
 */
fun testWhen2(any: Any) {
    when {
        (any is String) -> println("is String")
        ((any is Int) || (any is Float)) -> println("is not String")
        any is Boolean || any is Double -> println("is not int or float")
    }
}

fun test01(list: List<String>) {
    println("size: ${if (list.size > 10) "< 10" else ">= 10"}")
}

inline fun <reified T> newInstance(): T {
    return T::class.java.newInstance()
}

class ClsA {

    val a: Int by lazy {
        println("11112222")
        1
    }

//    init {
//        println("ClasA init === > ${a.javaClass}")
//    }

    fun log() {
        println("111:$a")
        val a = "222"
    }

    companion object {
        init {
            println("ClasA companion init === > ")
        }
    }

//    override fun equals(other: Any?): Boolean {
//        println("equals invoke === > ")
//        return true
//    }

}


