package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2022/3/26
 * @edit
 */

class Name(val name: String, var address: String) {

    companion object Ex {
    }


    fun printName(name: String): String {
        println("name is :$name")
        return name
    }
}

fun printName(name: String): String {
    return name
}

fun main() {

//    val cls = Name::class
//    for (member in cls.members) {
//        println("member:${member.name}")
//    }

//    val name = Name("chenhui")
//    val method = Name::printName
//    method.invoke(name, name.name)
//
//    val m = name::printName

    val p = Name::name
    val p2 = Name::address
    val ee = Name.javaClass
}
