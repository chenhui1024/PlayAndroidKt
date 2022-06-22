package cn.learn.android.myapplication.javacommukt

import android.widget.Button
import android.widget.TextView
import cn.learn.android.myapplication.click
import cn.learn.android.myapplication.javacommukt.callback.JavaCallBack
import cn.learn.android.myapplication.javacommukt.callback.JavaCallBackWrapper
import cn.learn.android.myapplication.javacommukt.callback.KotlinCallBack
import cn.learn.android.myapplication.javacommukt.callback.KotlinCallBackWrapper
import kotlin.reflect.KClass

/**
 * @description
 * @author ch
 * @date 2021/9/1
 * @edit
 */
class Utils {

    companion object {
        fun internalSout() {

        }
    }

    fun show() {
    }

}

fun sout(message: String) {
    println(message)
}

class AA : IJavaTest {

    override fun print(str: String?) {

    }

}

fun getStudent(flag: Int): Student? {
    if (flag == 0)
        return null
    else
        return Student()
}

fun main() {

    //1.kotlin调用java
    UtilsTest.`in`() //因为in在kotlin中是关键字，所以会自动加上这个‘’
    UtilsTest.out()

    //2.调用java class
    showClass(Student::class.java)

    //3.调用kotlin class
    showClassKt(KTStudent::class)

    //--------------------------------------------------------------------------------------------
    //所以kotlin中，要看对应实现的代码是java实现还是kotlin实现，如果是kotlin实现，那么callback就一种实现方式
    //4.kotlin使用java callback
    //有两种写法
    //①
    JavaCallBackWrapper().setCallBack(object : JavaCallBack {
        override fun print() {
        }
    })

    //②
    JavaCallBackWrapper().setCallBack {
    }

    //5.kotlin使用kotlin callback
    //只有一种写法
    KotlinCallBackWrapper().setCallBack(object : KotlinCallBack {
        override fun print() {
        }
    })
    //但是如果是函数对象，那就可以
    KotlinCallBackWrapper().setFunc {
    }
    //--------------------------------------------------------------------------------------------

    `123`()
}

fun showClass(cls: Class<Student>) {
}

fun showClassKt(kCls: KClass<KTStudent>) {

}

/**
 * 创建一个只有kotlin代码才能调用的方法
 * 反引号是Kotlin语言中对其的特殊处理，但是Java中是没有反引号语法的，所以你用反引号命名的变量和方法在Java中无法被调用的。
 * 所以如果你非要创建一些Java无法调用的变量和方法的话，就可以使用这样特殊的方式。
 *
 * 但是普通情况下，我们是不推荐这样的命名方式的。
 *
 * 作用：
 * ① 解决关键字冲突。
 * ② 强行将一个不合法的字符变为合法。
 */
fun `123`() {
}





