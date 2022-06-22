package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/15
 * @edit
 */
data class User(val id: Int, val name: String, val age: String)

fun User.judge() {
    fun check(value: String, fieldNmae: String) {
        if (value.isEmpty()) {
            println("Check error, user $id's $fieldNmae is null")
            return@check
        }
        println("check success")
    }

    check(name, "Name")
    check(age, "Age")
}

fun main() {
    val user = User(11, "ch", "28")
    user.judge()
}

