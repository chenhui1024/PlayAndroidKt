package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/3
 * @edit
 */

class Book {

    var name: String? = null
    set(value) {
        println("1111")
//        field = value
    }

}

class Student {

    fun test() {
        show("111")
        fun add(str1: String, str2: String): Int {
            return 1
        }

        val method = fun(str1: String, str2: String): Int {
            return str1.toInt() + str2.toInt()
        }

        methodWrapper("1", ::add, "2")
        methodWrapper("1", method, "2")
    }

}

fun Student.show(message: String) {
}

fun main() {
//    val student: Student = Student()
//    student.test()

    val book = Book()
    book.name = "22"
    println("name:${book.name}")
}

fun methodWrapper(str1: String, method: (String, String) -> Int, str2: String) {
    val result = method(str1, str2)
    println("result:$result")
}




