package cn.learn.android.myapplication

import android.icu.text.DateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @description
 * @author ch
 * @date 2022/1/9
 * @edit
 */
@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    testJavaTime()
    testJavaUtilTime()
}

fun testJavaUtilTime() {

    val list = listOf("dsadsa", "2eesa", "33dsadsa")
    list.map {
        it to "2"
    }.toMap()

    //1. 获取时间
//    val time = SimpleDateFormat.getDateTimeInstance(DateFormat.YEAR_FIELD, DateFormat.DATE_FIELD).format(Date())
//    val time = SimpleDateFormat("yyyy/MMM/dd HH:mm:ss", Locale.CHINESE).format(Date())
//    val time = SimpleDateFormat("yyyy/MMM/dd HH:mm:ss", Locale.CHINA).format(Date())
//    val time = SimpleDateFormat.getDateTimeInstance().format(Date())
//    val time = SimpleDateFormat.getTimeInstance().format(Date())
    //2. 获取其他时区的时间
//    val calendar = Calendar.getInstance()
//    calendar.clear()
//    calendar.time = Date(System.currentTimeMillis())
//    calendar.timeZone = TimeZone.getTimeZone("America/New_York")
//    val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
//    format.timeZone = TimeZone.getTimeZone("America/New_York")
//    val time = format.format(calendar.time)
//    println("time:$time")

}

@RequiresApi(Build.VERSION_CODES.O)
fun testJavaTime() {
    //1.获取某个时区的时间
//    val time = ZonedDateTime.now(ZoneId.of("America/New_York")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
//    val time = LocalDateTime.now(ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
//    val time = ZonedDateTime.now(ZoneId.of("America/New_York")).toLocalDateTime().format(
//        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
//    println("time:$time")

    //2.获取当前时区时间
//    val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
//    val time = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)
//    println("time:$time")
}




