package com.example.playandroid_kt.rengwuxian.lesson3

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.lesson2.SingleInstance
import kotlinx.android.synthetic.main.activity_lesson3.*
import java.lang.Exception
import java.lang.RuntimeException

/**
 * @description
 * @author ch
 * @date 2020/7/3
 * @edit
 */
class Lesson3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson3)
        var user = User(111, "222")
        Log.d("chenhui", "user age:${user.age},name:${user.name}")

        user = User()
        print("user age:${user.age},name:${user.name}")

        //如果存在多个参数的时候，需要使用“命名参数”的方法来调用
        logt(log = "测试打印输出")

        logt("ch", "测试打印输出")

//        logt(tag = "ch", "111")//如果混用位置参数与命名参数，那么所有的位置参数都应该放在第一个命名参数之前
        //正确写法
        logt("ch", log = "111")

        intervalFun()

        listOpr()

        var privateCls = PrivateCls.getInstance()

        var man: Man = Man("chenhui")
        man.age = 99
        print("man name:${man.name}, age:${man.age}")

        printZYStr()

//        findViewById<Button>(R.id.btn_test).setOnClickListener(object : View.OnClickListener {
//
//            override fun onClick(v: View?) {
//                Log.d("chenhui", "btn name is : ${v?.id}")
//            }
//
//        })

        var btn: Button = findViewById(R.id.btn_test)
//        btn.setOnClickListener{
//            Log.d("chenhui", "btn name is : ${it.id}")
//        }

//        btn.setOnClickListener { btnView ->
//            run {
//                Log.d("chenhui", "btn name is : ${btnView.id}")
//            }
//        }

        btn.setOnClickListener {view ->
            Log.d("chenhui", "666666666666666:${view.id}")
        }

        testRange()

        testSequence()

        testIfElse()

        val r = testWhen(10)
        println("test when result is $r")

        testTryCatch()

        testElvis()

        testEquals()
    }

    //如果只有一行代码，大括号可以不写
    fun print(log: String) = Log.d("chenhui", log)

    //带默认值的函数
    fun logt(tag: String = "chenhui", log: String) {
        Log.d(tag, log)
    }

    //可以在函数内部定义函数，类似局部变量一样
    fun intervalFun() {

        var list = listOf("111", "222")

        fun log(log: String) {
            Log.d("chenhui", "interval log:$log")
        }

        for (value in list) {
            log(value)
        }

        //遍历的另一种方式
        list.forEach { item ->
            Log.d("chenhui", "list start")
            Log.d("chenhui", "item:$item")
            Log.d("chenhui", "list end")
         }

    }

    fun listOpr() {
        val list = listOf("111", "222", "333")

        //在Kotlin的闭包中，可以使用很多方式来指定参数，如果没有指定参数的话，默认会有一个it参数来代表闭包的参数
        list.filter { it.equals("222") }
            .map { it + ".txt" }
            .forEach { Log.d("chenhui", "key:$it") }
    }

    //本地函数（嵌套函数）
    fun login(name: String, password: String, illegalStr: String) {

        //以前的写法：
//        if (TextUtils.isEmpty(name)) {
//            throw RuntimeException(illegalStr)
//        }
//        if (TextUtils.isEmpty(password)) {
//            throw RuntimeException(illegalStr)
//        }

        //现在可以采用内部函数的方法
        fun validate(content: String) {
            if (TextUtils.isEmpty(content)) {
//                throw RuntimeException(illegalStr)
                throw InputEmptyException(illegalStr)
            }
        }
        validate(name)
        validate(password)

    }

    //当存在其他构造函数的时候，主构造函数是可以省略不写的
    //当没有子构造函数的时候，应该写成
//    class InputEmptyException: Exception() {
    class InputEmptyException: Exception {

        constructor(errorDesc: String) : super(errorDesc)

    }

    //有时候转义字符也想实际输出
    fun printZYStr() {
//        val str = """111 \n 222 \n """ //输出结果喝原字符串一样

        //这里的 trimMargin() 函数有以下几个注意点：
        //| 符号为默认的边界前缀，前面只能有空格，否则不会生效
        //输出时 | 符号以及它前面的空格都会被删除
        //边界前缀还可以使用其他字符，比如 trimMargin("/")，只不过上方的代码使用的是参数默认值的调用方式
        val str = """111 \n 222 
                |\n 
                |dsahdshadjas   """.trimMargin()
        //输出:
        //111 \n 222
        //\n
        //dsahdshadjas

        println(str)
    }


    fun testRange() {

        //kotlin已经不支持这种写法了，要使用rangge
//        for (int i=0; i<10; i+) {
//
//        }

        //Range 这个东西，天生就是用来遍历的：
        //默认步长为 1，输出：0, 1, 2, 3, 4, 5, 6, 7....1000,

        //表示从0到100，闭区间，包括0和100
        //除了这里的 IntRange ，还有 CharRange 以及 LongRange。
        println("0..100 ==== > ")
        val range: IntRange = 0..100
        for (i in range) {
            println("i:$i")
        }

        //0-100，不包括100
        println("0 until 100 ==== > ")
        val rg: IntRange = 0 until 100
        for (i in rg) {
            println("i:$i")
        }

        //步长为2
        println("0 until 100 step 2 ==== > ")
        val r: IntRange = 0 until 100
        for (i in r step 2) {
            println("i:$i")
        }

        //以上是递增区间，Kotlin 还提供了递减区间 downTo ，不过递减没有半开区间的用法:
        //其中 10 downTo 1 就表示递减的闭区间 [10, 1]。这里的 downTo 以及上面的 step 都叫做「中缀表达式」
        print("downTo === > ")
        for (i in 10 downTo 1) {
            println("i:$i")
        }

        print("downTo step 2=== > ")
        for (i in 10 downTo 1 step 2) {
            println("i:$i")
        }
    }

    //序列 Sequence 又被称为「惰性集合操作」
    //惰性的概念首先就是说在「👇」标注之前的代码运行时不会立即执行，它只是定义了一个执行流程，
    // 只有 list2 被使用到的时候才会执行
    //而List则不存在惰性问题，它声明之后就会立即执行
    //而这里必须强调一下，Sequence的性能比list是高很多的
    fun testSequence() {

        val list: Sequence<Int> = sequenceOf(1, 2, 3, 4, 5)
            .map { it * 2 }

        list.forEach {
            //打印出2，4，6，8，10
            Log.d("chenhui", "testSequence it:$it")
        }

        //惰性指当出现满足条件的第一个元素的时候，Sequence 就不会执行后面的元素遍历了，即跳过了 5 的遍历。???待验证
        val list2: Sequence<Int> = sequenceOf(1, 2, 3, 4, 5)
            .map {
                it * 2
            }
            .filter { it > 7 }
        println("map filter:${list2.first()}")//只取第一个。这里才开始执行list之前的代码


        val sequence = sequenceOf(1, 2, 3, 4)
        val result: Sequence<Int> = sequence
            .map { i ->
                println("Map $i")
                i * 2
            }
            .filter { i ->
                println("Filter $i")
                i % 2  == 0
            }

        result.forEach {
            println("sequence result:$it")
        }

        (0..100).asSequence()
            .map { it + 1 }
            .filter { it > 90 }
            .run {
                println("it:$this")
            }

    }

    //Kotlin 中弃用了三元运算符（条件 ? 然后 : 否则），不过我们可以使用 if/else 来代替它。
    fun testIfElse() {

        fun max(num1: Int, num2: Int): Int {
            //原先写法
            if (num1 > num2) {
                return num1
            } else {
                return num2
            }
        }

        var num1: Int = 1
        var num2: Int = 2
        //原写法
//        var max: Int = max(num1, num2)

        //现在写法
        var max: Int = if (num1 > num2) num1 else num1
        //也可以是代码块
        max = if (num1 > num2) {
            print("max is $num1")
            num1 //返回num1
        } else {
            print("max is $num2")
            num2 //返回num2
        }
    }

    /**
     * 这里与 Java 相比的不同点有：
    ①   省略了 case 和 break，前者比较好理解，后者的意思是 Kotlin 自动为每个分支加上了 break 的功能，
        防止我们像 Java 那样写错
    ②   Java 中的默认分支使用的是 default 关键字，Kotlin 中使用的是 else
     */
    fun testWhen(status: Int): Int {
        when (status) {
            1 -> {
                println("testWhen in is $status")
                return status.plus(1)
            }
            2 -> {
                println("testWhen in is $status")
                return status.plus(2)
            }
            3,4,5 -> {
                println("testWhen in is $status")
                return status.plus(3)
            }
            else -> {
                println("testWhen in is $status")
                return status.plus(10)
            }
        }

        //when和if/else一样，也可以作为表达式方式
//        var result = when (status) {
//            1 -> {
//                println("testWhen in is $status")
//                return status.plus(1)
//            }
//            2 -> {
//                println("testWhen in is $status")
//                return status.plus(2)
//            }
//            else -> {
//                println("testWhen in is $status")
//                return status.plus(10)
//            }
//        }
//        return result

        //在 when 语句中，我们还可以使用表达式作为分支的判断条件：
//        when (status) {
//            in 1..10 -> {
//                println("testWhen in is $status")
//                return status.plus(1)
//            }
//            in listOf(20, 30) -> {
//                println("testWhen in is $status")
//                return status.plus(2)
//            }
//            //不在30-40之间
//            !in 30..40 -> {
//                println("testWhen in is $status")
//                return status.plus(3)
//            }
//            else -> {
//                println("testWhen in is $status")
//                return status.plus(10)
//            }
//        }

        //判断类型
//        when (status) {
//            is Int -> {
//
//            }
//            else -> {
//
//            }
//        }

        //还可以省略 when 后面的参数，每一个分支条件都可以是一个布尔表达式：
        //当分支的判断条件为表达式时，哪一个条件先为 true 就执行哪个分支的代码块。
//        return when {
//            status>10 -> {
//                10
//            }
//            status==20 -> {
//                20
//            }
//            else -> {
//                30
//            }
//        }
    }

    //Kotlin 中的异常是不会被检查的，只有在运行时如果 string2Int("1e0") 抛出异常，才会出错。
    fun testTryCatch() {

        var str: String = "1e0"
        //原先做法
        fun string2Int(str: String): Int {
            try {
                var number: Int = str.toInt()
                return number
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return 0
        }
        //kotlin中并不会提示抛出异常
//        var intNum = string2Int(str)

        //现在的做法：
        var intNum: Int? = try { string2Int(str) } catch (e: Exception) { null }
    }

    fun testElvis() {
        val str: String? = "hello"
        //这样做就不行了，因为当你str为空的时候，就不会执行后面的.length方法
        //所以引入?:
//        var num: Int = str?.length
        //当str为空的时候，直接返回-1
        var num: Int = str?.length ?: -1


        //?: 还有一个用法
        fun print(person: Person) {
            //这里就相当于是java中的  if (TextUtils.isEmpty(person.age)) return
            val age = person.age ?: return
            println("print person age:$age")
        }

    }

    //我们知道在 Java 中，== 比较的如果是基本数据类型则判断值是否相等，
    // 如果比较的是 String 则表示引用地址是否相等， String 字符串的内容比较使用的是 equals() ：
    fun testEquals() {
        //在kotlin中
        //== ：可以对基本数据类型以及 String 等类型进行内容比较，相当于 Java 中的 equals
        //=== ：对引用的内存地址进行比较，相当于 Java 中的 ==

        var str1 = "111"
        var str2 = "111"
        println("str1 == str2:${str1 == str2}") //返回true

        var str3 = "333"
        var str4 = str3
        var str5 = str3
        println("str4 === str5:${str4 === str5}")//返回true

    }


}