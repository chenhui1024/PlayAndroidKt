package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/23
 * @edit
 */
fun main() {
    //初始版本
//    val result = buildString01 {
//        it.append("44")
//        it.append("55")
//        it.append("66")
//    }
//    println("result:$result")


    //第二个版本
//    val result = buildString {
//        append("445566")
//    }
//    println("result:$result")

    //带一个参数的 版本
//    val result = buildStringEx { internalStr ->
//        println("内部传过来的数据：$internalStr")
//        return@buildStringEx 10
//    }

    //带两个参数的版本
//    val result = buildStringEx { internalString, intData ->
//        println("内部传过来的数据：$internalString,$intData")
//        return@buildStringEx 20
//    }
//    println("result:$result")


//    val invoke = TestInvoke()
//    invoke.invoke("111")
//    invoke("222")

   val list = listOf("22", "11", "33")
    val invoke = TestInvoke2()
    for (str in list.filter(invoke)) {
        println("str:$str")
    }
}

/**
 *   初始版本
 */
fun buildString01(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder("112233")
    builderAction(sb)
    return sb.toString()
}

/**
 *   第二个版本
 */
fun buildString02(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder("112233")
//    sb.builderAction()
//    builderAction.invoke(sb)
    builderAction(sb)
    return sb.toString()
}

//带参数的版本
fun buildStringEx(action: StringBuilder.(String, Int) -> Int): String {
    val sb = StringBuilder("newBuildAction")
    val result = sb.action(" append string", 22)
    println("buildStringEx result:$result")
    return sb.toString()
}

class TestInvoke {

    operator fun invoke(name: String) {
        println("invoke :$name")
    }

}

class TestInvoke2 : (String) -> Boolean {
    override fun invoke(p1: String): Boolean = if ("11" == p1) true else false
}




