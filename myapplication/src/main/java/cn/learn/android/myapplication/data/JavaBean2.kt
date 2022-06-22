package cn.learn.android.myapplication.data

/**
 * @description
 * @author ch
 * @date 2021/9/3
 * @edit
 */
class JavaBean2(val name: String, val sex: Char = 'M') {

    //模仿data class。其中component名字不能变
    operator fun component1(): String = name

    operator fun component2(): Char = sex

    operator fun component3(): String = "222"

}