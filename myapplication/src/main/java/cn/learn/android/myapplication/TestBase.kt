package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/8/30
 * @edit
 */
class Test1 {

//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            println("111")
//        }
//    }

}

fun main() {
    //TODO 1.测试可变长参数
    println("add result is ${add(1, 2, 3)}")

    //TODO 2.测试匿名内部函数
    val addFunc: (Int, Int) -> Int = { num1, num2 ->
        num1 + num2
    }
    println("addFunc result is ${addFunc(1, 2)}")

    //TODO 3.测试打印换行 三个“
    //① 不带空格
    println("""
        --- start ---
        11111
        2222
        result is ${add(1, 2, 3, 4, 5, 6)}
        --- end ---
    """.trimIndent())

    //② 前置空格
    println("""
--- start ---
11111
        2222
        result is ${add(1, 2, 3, 4, 5, 6)}
        --- end ---
    """)

    //TODO 4.打印美元符
    println("消费金额：${'$'}9999.99")

    //TODO 5.null检查
    val name: String ?= null
    var age: String ?= null
    println("name length is:${name?.length}")
    //不想打印null
    age = "28"
    println("name length is ${name?.length ?: age?.length ?: "111"}")

    //TODO 6.区间
    val start = 10
    val end = 20
    for (i in start until end step 2) {
        println("i:$i")
    }
}

fun add(vararg nums: Int): Int {
    var result = 0
    for (num in nums) {
        result += num
    }
    return result
}
