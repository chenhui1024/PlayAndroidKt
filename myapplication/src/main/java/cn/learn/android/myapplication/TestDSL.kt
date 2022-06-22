package cn.learn.android.myapplication

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @description
 * @author ch
 * @date 2021/9/24
 * @edit
 */
fun main() {
//    val s = "112233"
//    val result = s should startWith("22")
//    println("result:$result")
}

/**
 *  @return the result show
 *  @param matcher
 */
private infix fun <T> T.should(matcher: Matcher<T>): Boolean = matcher.test(this)

interface Matcher<T> {
    fun test(t: T): Boolean
}

class startWith(private val prefix: String) : Matcher<String> {

    override fun test(t: String): Boolean {
        return t.startsWith(prefix)
    }

}

