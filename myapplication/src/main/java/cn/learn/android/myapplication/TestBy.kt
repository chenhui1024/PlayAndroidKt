package cn.learn.android.myapplication

import kotlin.reflect.KProperty

/**
 * @description
 * @author ch
 * @date 2021/9/16
 * @edit
 */

fun main() {
//    val list = IListWrapper()
//    list.add("chenhui")
//    list.remove("111")

    //2. 测试类委托
//    val list = IListWrapperWithT<String>()
//    list.add("ccc")
//    list.remove("112")

    //3.属性委托
//    val str: String by lazy { "111" }

    //4.写一个自己的lazy：myLazy
    val bean: Bean by myLazy { Bean() }
    println("bean:$bean") //这个对象如果没使用，是不会调用后面myLazy的函数的
}

/**
 *  ==========================================myLazy start==========================================
 */
class Bean

interface Delegate<T> {
    val value: T

    operator fun getValue(thisRef: T?, property: KProperty<*>): T
}

class DelegateImpl<T>(val init: () -> T) : Delegate<T> {

    private var _value: T ?= null

    override val value: T
        get() {
            println("myLazy getValue === > ")
            if (_value != null) {
                return _value!!
            }
            _value = init()
            return _value!!
        }

    override fun getValue(thisRef: T?, property: KProperty<*>): T = value

}

fun <T> myLazy(init: () -> T): Delegate<T> = DelegateImpl(init)

//operator fun <T> Delegate<T>.getValue(thisRef: Any?, property: KProperty<*>): T = value

/**
 *  ==========================================myLazy end==========================================
 */

interface IList<T> {
    fun add(t: T)
    fun remove(t: T)
}

class IlistImpl : IList<String> {
    override fun add(t: String) {
        println("add:$t")
    }

    override fun remove(t: String) {
        println("remove:$t")
    }
}

class IListWrapper(val iList: IList<String> = IlistImpl()) : IList<String> by iList {

    override fun add(t: String) {
        println("before add === > ")
        iList.add(t)
        println("after add === > ")
    }

}

class IListWithT<T> : IList<T> {
    override fun add(t: T) {
        println("add:$t")
    }
    override fun remove(t: T) {
        println("remove:$t")
    }
}
class IListWrapperWithT<T>(val list: IListWithT<T> = IListWithT()) : IList<T> by list {

    override fun add(t: T) {
        println("before add === > ")
        list.add(t)
        println("after add === > ")
    }
}

interface Tmp {
    fun printEx()
}
class TmpImpl : Tmp {
    override fun printEx() {
    }
}
class TmpWrapper : Tmp by TmpImpl() {

}




