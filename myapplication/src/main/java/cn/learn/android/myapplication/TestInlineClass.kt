package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/13
 * @edit
 */
fun main() {
    val hour = Hour(2)
    println("2 hour is ${hourToMinutes(hour)} minutes, ${hour.toSeconds()} seconds")

    for (i in 0..9) {
        test(Hour(i))
    }
}

fun test(hour: Hour) {
    println("hour:${hour.hour}")
}

fun hourToMinutes(hour: Hour): Int {
    return hour.toMinuters()
}

interface IntA {
    val minute: Int
}

@JvmInline
value class Minute(override val minute: Int) : IntA

@JvmInline
value class Hour(val hour: Int) {
    fun toMinuters() = hour * 60

    fun toSeconds() = toMinuters() * 60
}
