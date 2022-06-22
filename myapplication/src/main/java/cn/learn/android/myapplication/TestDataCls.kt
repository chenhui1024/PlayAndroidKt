package cn.learn.android.myapplication

import cn.learn.android.myapplication.data.JavaBean
import cn.learn.android.myapplication.data.JavaBean2

fun main() {

//    val bean = JavaBean(11, "ch")
//    println(bean.name)
//    bean.id = 12
//
//    val(myId, myName) = bean.copy()
//    println("myId:$myId, myName:$myName")
////
////    //下划线表示拒收
//    val(_, myName2) = bean.copy()
//    println("myName2:$myName2")
//
//    val javaBean2 = JavaBean2("ch", 'M')
//    val (v1, v2, v3) = javaBean2
//    val (v4, v5) = javaBean2
//    println("v1:$v1, v2:$v2, v3:$v3")
//    println("v4:$v4, v5:$v5")

//    val newPoint = NewPoint(1, 1)
//    val (x, y) = newPoint
//    println("x:$x, y:$y")
}

class NewPoint(val x: Int, val y: Int) {

    operator fun component1() = x
    operator fun component2() = y

}



