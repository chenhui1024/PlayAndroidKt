package cn.learn.android.myapplication

import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @description
 * @author ch
 * @date 2021/9/21
 * @edit
 */
fun main() {
//    printSum2(listOf(1, 2, 3))
//    printSum2(setOf(1, 2, 3))
//    println("finished === > ")


//    val info = newInstanceEx<TmpInfo>()
//    println("info:$info")

//    feedAll(Herd<Cat>())
//    feedOne(Cat())

//    val cats = arrayListOf(Cat())
//    val animals: MutableList<Cat> = cats
//    testListxx(arrayListOf(Cat(), Cat()), arrayListOf(Cat()))


//    val baseAdapter = BaseRVAdapter<SimpleAdapter.SimpleViewHolder<Entity>, Entity>()
    val baseAdapter = SimpleAdapter<Entity>()
//    val baseAdapter = BaseRVDBAdapter<SimpleAdapter.SimpleViewHolder<Entity>, ViewDataBinding, Entity>()
    baseAdapter.print()
}

class Entity {

}

abstract class AbstractAdapter<VH : BaseViewHolder<E>, E> {

    /**
     * 获取泛型实际类型列表
     */
    protected fun getActualTypeList(): Array<Type> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments

    abstract fun getViewHolderClass(): Class<VH>

    fun print() {
        val cls = getViewHolderClass()
        println("cls:$cls")
    }

}

open class BaseRVAdapter<VH : BaseViewHolder<E>, E> : AbstractAdapter<BaseViewHolder<E>, E>() {

    override fun getViewHolderClass(): Class<BaseViewHolder<E>> {
        @Suppress("UNCHECKED_CAST")
        val cCls = getActualTypeList()[0]
        println("cCls:$cCls")
        val cls = (getActualTypeList()[0] as ParameterizedType).rawType as Class<BaseViewHolder<E>>
        println("BaseRVAdapter:$cls")
        return cls
    }

}

open class BaseRVDBAdapter<VH : BaseRvDBViewHolder<DB, E>, DB : ViewDataBinding, E> : BaseRVAdapter<VH, E>() {

//    override fun getViewHolderClass(): Class<BaseViewHolder<E>> {
//        @Suppress("UNCHECKED_CAST")
//        val a = getActualTypeList()[0]
//        println("a:$a")
//        val cls = ((getActualTypeList()[0] as ParameterizedType).rawType) as Class<BaseViewHolder<E>>
//        println("BaseRVAdapter:$cls")
//        return cls
//    }

}

class SimpleAdapter<E> : BaseRVDBAdapter<SimpleAdapter.SimpleViewHolder<E>, ViewDataBinding, E>() {

    override fun getViewHolderClass(): Class<BaseViewHolder<E>> {
        @Suppress("UNCHECKED_CAST")
        val cls = ((getActualTypeList()[0] as ParameterizedType).rawType) as Class<BaseViewHolder<E>>
        println("SimpleAdapter2:$cls")
        return cls
    }

    class SimpleViewHolder<E> : BaseRvDBViewHolder<ViewDataBinding, E>() {
    }

}


abstract class BaseViewHolder<E> {

    abstract fun bindData(entity: E)

}

open class BaseRvDBViewHolder<DB : ViewDataBinding, E> : BaseViewHolder<E>() {

    override fun bindData(entity: E) {

    }

}

open class ViewDataBinding {
}


fun <T : Animal> testListxx(source: MutableList<out T>, dest: MutableList<in T>) {
}

fun <T> copyData(source: MutableList<out T>, dest: MutableList<in T>) {
    //MutableList本身对T是没有in或out修饰的。但是你添加了out之后，就变成只能使用在out位置的方法
    //添加了in之后，就只能使用在in位置的方法
    for (data in source) {
        dest.add(data)
    }
}

fun <T : Animal> feedOne(animal: T) {
    animal.feed()
}

open class Animal {
    fun feed() {
    }
}

class Herd<out T : Animal>(private val t: T) {

    fun get(): T {
        return t
    }

    private fun set(t: T) {
    }

//    fun set2(t: T) {
//    }
}

class Cat : Animal() {
}

fun feedAll(animals: Herd<Animal>) {
}

class TmpInfo {

}

inline fun <reified T> newInstanceEx(): T {
    return T::class.java.newInstance()
}

fun printSum(collection: Collection<*>) {
    val intList = collection as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println("sum:${intList.sum()}")
}

fun printSum2(collection: Collection<Int>) {
    val intList = collection as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println("sum:${intList.sum()}")
}

inline fun <reified T> isSpecialType(any: Any) = any is T
