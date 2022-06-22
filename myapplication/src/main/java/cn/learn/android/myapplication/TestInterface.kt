package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/15
 * @edit
 */

interface Clickable {

    val value: String

    fun click()
    fun show() = println("value is $value")
}

interface Focusable {
    fun show() = println("i am focusable")
}

class Button : Clickable, Focusable {

    override val value: String
        get() {
            return "111"
        }

    override fun click() {
        println("click === > ")
    }

    override fun show() {

    }

}

fun main() {
//    val btn = Button()
//    btn.show()

//    val exprImpl = ExprImpl()
//    println("value:${exprImpl.value}, and name:${exprImpl.name}")

    val info = Info()
    println("info name:${info.name}")
    info.name = "333"
    println("info name:${info.name}")

    println("info age:${info.age}")
//    info.age = 22//不允许
}

/**
 *  接口声明了，子类需要提供获取name的方法
 */
interface IA {
    val name: String
}

//方式1去实现
class IAImpl : IA {

    //要注意的是，因为没有字段去存储这个值，所以每次调用的时候都会去计算
    override val name: String
        get() {
            return "111"
        }

}

/**
 *   方式2去实现
 */
class IAImpl2(override val name: String) : IA {

}

/**
 *   方式3
 */
class IAImpl3 : IA {
    //要注意的是，因为没有字段去存储这个值，所以每次调用的时候都会去计算
    override val name: String
        get() = "333"
}

/**
 *   接口还可以有这样的实现
 */
interface Expr {

    val value: String
    val name: String
    get() {
        return value.substringBefore("|")
    }

}

class ExprImpl : Expr {
    override val value: String
        get() = "112|3334"
}

class Info {

    var name: String? = null
    get() {
        println("重写get方法，就是为了每次在获取值的时候，打印这些内容")
        return field
    }
    set(value) {
        println("重写set方法，就是为了在每次设置值的时候，打印这些内容")
        field = value
    }

    var age: Int = 0
    private set

}
