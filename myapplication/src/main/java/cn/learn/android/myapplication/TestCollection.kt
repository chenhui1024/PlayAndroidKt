package cn.learn.android.myapplication

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import java.util.function.UnaryOperator

/**
 * @description
 * @author ch
 * @date 2021/9/14
 * @edit
 */

data class People(val name: String, val age: Int, val friends: List<String> = arrayListOf())

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
//    testList()

//    testMap()

//    testSort()

    testFlatMap()

//    ()

    //myWith
//    val people = People("ch", 28)
//    myWith(people) {
//        println("name:$name")
//    }

//    val emptyStr: String? = null
//    println("emptyStr:${emptyStr.isInvalidStr()}")
}

fun String?.isInvalidStr(): String {
    return this ?: ""
}

inline fun <T, R> myWith(t: T, block: T.() -> R): R {
    //两个都可以
//    return t.block()
    return block(t)
}

/**
 *   创建一个序列
 */
fun createSeq() {
//    val naturalNumbers = generateSequence(0) { it + 1 }
//    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
//    val sum = numbersTo100.sum()
//    println("sum:$sum")

    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Maria", 31))
    val resultList = people.asSequence()
        .map(Person::name)
        .filter { it.length > 3 }
        .toList()
}

class Person(val name: String, val age: Int)


private class JavaBook(val title: String, val autuors: List<String>)

fun testTmp() {
    val books = listOf(JavaBook("第一部曲", listOf("zhangsan", "Lisi")),
        JavaBook("第二部曲", listOf("zhangsan", "wangwu")),
        JavaBook("第三部曲", listOf("zhaoliu"))
    )
    books.flatMap { it.autuors }.toSet()

//    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Maria", 31))
//    val map = people.groupBy { it.age }
//    println(people.find { it.age >= 29 })
//    println(people.count { it.age > 30 })
//    println(people.any { it.age > 30 })
//    println(people.all { it.age > 30 })
//    println(people.filter { it.age > 30 }.map(Person::age))
//    println(people.filter { it.age > 30 })
}

fun createRunnable(): Runnable {
    return Runnable {
        println("something!")
    }
}

fun testFlatMap() {


    val onClickListener = View.OnClickListener {
        println("onClick!")
    }


//    val list = listOf(1, 2, 3, 4)
//    list.map { it * it }.filter { it > 3 }
//    list.asSequence().map { it * it }.filter { it > 3 }

    //1.map
//    val peoples = listOf(People("chenhui", 28, listOf("cc", "bb")), People("fx", 27, listOf("aa", "dd")), People("ch", 28))

    //执行效率是不一样的。
//    peoples.asSequence().filter { it.name.length > 2 }.map(People::name).toList()
//    peoples.asSequence().map(People::name).filter { it.length > 2 }.toList()

    //map
//    val names = peoples.map { it.name }
//    println("names:$names")

    //flatmap
//    val friends = peoples.flatMap { it.friends }.toSet()
//    println("all friends:$friends")

    //sequence
//    val specialPeoples = peoples.asSequence().map(People::age).filter { it > 27 }.toList()
//    println("specialPeoples:$specialPeoples")

    //2.filter + map
//    val people = peoples.maxByOrNull { it.age }
//    val maxPeoples = peoples.filter { it.age == people!!.age }
//    println("maxPeoples:$maxPeoples")

    //3.filterKeys mapKeys filterValues mapValues
//    val map = mapOf(1 to "2", 3 to "4")

//    val keys = map.filterKeys { it > 1 }
//    println("keys:$keys")

//    val fillMaps = map.filterValues { it == "2" }
//    println("fillMaps:$fillMaps")

//    val newMaps = map.mapKeys { it.key + 1 }
//    println("newMaps:$newMaps")
}

/**
 *   maxBy：实现排序
 */
fun testSort() {

    /**
     *   find max
     */
    val peoples = listOf(People("chenhui", 28), People("fx", 27), People("zs", 20))
//    val people = peoples.maxByOrNull {
//        it.age
//    }
    val people = peoples.maxByOrNull(People::age)
    println("max age people name is ${people!!.name}, age is ${people.age}")

    /**
     *   find last
     */
    val p = peoples.findLast {
        it.age > 21
    }
    p?.let {
        println("find last:${it.name}")
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun testList() {
    val r = listOf("1", "2")
    println("r:${r.javaClass}")
    for (s in r) {
        println("s:$s")
    }

    //这里证明了，listof实际返回的是java.util.Arrays$ArrayList
    //不过kotlin返回值是kotlin的List，所以就丢失了部分接口
//    val mutableCollection: MutableCollection<String> = r as MutableCollection<String>
    val arrayList: java.util.AbstractList<String> = r as java.util.AbstractList<String>
//    arrayList.remove("1")
//    arrayList.clear()
    println("=============${arrayList.size}")
    arrayList.replaceAll(@RequiresApi(Build.VERSION_CODES.N)
    object : UnaryOperator<String> {
        override fun apply(t: String): String {
            return when (t) {
                "1" -> "3"
                "2" -> "4"
                else -> "5"
            }
        }
    })
    for (s in r) {
        println("s:$s")
    }
}

fun testMap() {
    val map = mapOf(1 to "2", 3 to "4")
    println("map${map.javaClass}")
    println("遍历1")
    for (entry in map) {
        println("key:${entry.key}, value:${entry.value}")
    }

    println("遍历2")
    for ((k, v) in map) {
        println("key:${k}, value:${v}")
    }
}

fun testSet() {
    val set = setOf("1", "2")
    for (s in set) {
        println("s:$s")
    }
//    println("set:${set.javaClass}")
    val linkedSet: java.util.LinkedHashSet<String> = set as java.util.LinkedHashSet
    linkedSet.clear()
    println("================${set.size}")
    for (s in set) {
        println("s:$s")
    }
}




