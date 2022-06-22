package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/13
 * @edit
 */

fun main() {

}

/**
 *  泛型参数是可能为空的，即使T后面不带？。因为可空类型，本身也可以为T
 *  这里因为没有指定上界，所以即使你t.hashCode()也不会报错
 */
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}

/**
 * 这里的t，不是非空了，你加上？反而提示你不需要
 */
fun <T : Any> printHashCodeAny(t: T) {
    println(t.hashCode())
}



