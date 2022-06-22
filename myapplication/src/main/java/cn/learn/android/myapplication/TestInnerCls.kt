package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/8/31
 * @edit
 */
class TestInnerCls {

    private val name = "111"

    class ClsA {
        fun show() {

        }
    }

    inner class ClsB {
        fun show() {
            println(name)
        }
    }

}

fun main() {
    //报错
//    val clsB = TestInnerCls.ClsB()
    val clsA = TestInnerCls.ClsA()
}