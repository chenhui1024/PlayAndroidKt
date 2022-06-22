package cn.learn.android.myapplication

import java.io.InputStream

/**
 * @description
 * @author ch
 * @date 2021/9/9
 * @edit
 */
fun main() {

    //方式1
//    var method01: (Int, Int) -> Int = fun(num1: Int, num2: Int): Int{
//        return num1+ num2
//    }
//
//    //方式2
//    var method02: (Int, Int) -> Int = {num1, num2 ->
//        num1 + num2
//    }
//
//    //方式3
//    var method03 = {num1: Int, num2: Int -> Int
//        num1 + num2
//    }
//    var method05 = {num1: Int, num2: Int -> num1 + num2}
//
//    //方式4
//    fun m(num1: Int, num2: Int): Int = num1 + num2
//    var method04 = ::m
//
//    var name: String? = null
//    println("name:${name.isChEmpty()}")

//    var value = 0
//    setFunc {
//        value ++
//    }

//    val women = Women(11)
//    val getAge = Women::age
//    getAge(women)
//    fun1(getAge)

//    val createWomen = ::Women
//    createWomen(11)

//    val women = Women(11)
//    val getAge = women::age
//    val age = getAge()
//    println("age:$age")

    val resultMethod = convertGjFun { str -> str.toInt() }
//    println("$resultMethod")
//    resultMethod.invoke(1)

//    val list = listOf("1", "2")
//    list.forEach label@{
//        println("forEach:$it")
//        if (it == "1") {
//            println("return === > ")
//            return@label
//        }
//    }
//    println("main func finished.")

    val list = listOf("1", "2")
    list.forEach(fun (s) {
        println("forEach:$s")
        if (s == "1") {
            println("return === > ")
            return
        }
    })
    println("main func finished.")
}

inline fun convertGjFun(inMethod: (String) -> Int): Int {
    val result = inMethod("111")
    return result
//    fun outMethod(num: Int): String = num.toString()
//    return ::outMethod
//    return {num: Int -> num.toString()}
//    return {num -> num.toString()}
}

@JvmInline
value class AA(val a: Int) {

}

open class Women(val age: Int) {

}

fun fun1(method: (Women) -> Int) {
}

fun setFunc(method: () -> Unit) {
}

fun getClickCount(): Int {
    var counts = 0
    setFunc {
        counts ++
    }
    return counts
}

fun String?.isChEmpty(): String {
    return this ?: "chenhui"
}




