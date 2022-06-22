package cn.learn.android.myapplication

import java.lang.UnsupportedOperationException

fun main() {

    val a = 10
    if (a in intArrayOf(9, 10, 11)) {
        println("in...")
    } else {
        println("out...")
    }

//    val array: Array<String> = Array(10) { num ->
//        "111" + num
//    }
//
//    for (s in array) {
//        println("array:$s")
//    }

//    testLabelFor()

//    testFor()

//    printAZArray()

//    createNulArray()

//    arrayToVararg()

//    val array = intArrayOf(1, 2, 3)
//    val intArray = Array<Int>(10) {
//        0
//    }
//    intArray.toIntArray()//需要的时候，可以转换成基本数据类型，不装箱
}

fun arrayToVararg() {
    val strings = listOf("111", "222", "333")
    println(String.format("%s/%s/%s", *strings.toTypedArray()))
}

fun createNulArray() {
    val arrayWithNull = arrayOfNulls<String>(10)
    arrayWithNull.set(0, "111")
    arrayWithNull[1] = "222"
    arrayWithNull[2] = null
    for (s in arrayWithNull) {
        s?.let {
            println(it)
            1
        }
    }
}

fun printAZArray() {

    fun createAZArray() = Array(26) {
        'a' + it
    }

    val charArray = createAZArray()
    for (c in charArray) {
        print("$c")
    }
    print("\n")
}


/**
 *   测试标签
 */
fun testLabelFor() {
    externalFor@ for (i in 1..10) {
        for (j in 1..10) {
            println("i:$i,j:$j")
            if (i == 5) {
                break@externalFor
            }
        }
    }
}

/**
 *   测试list遍历
 */
fun testFor() {
    var list = listOf("1", "2", "3", "4")
    //第一种方式
    for (s in list) {
        println(s)
    }

    //第二种方式
    list.forEach { item ->
        println(item)
    }

    //第三种方式
    for (index in list.indices) {
        println("下标：$index,值为：${list[index]}")
    }

}

