package cn.learn.android.myapplication.javacommukt.callback

/**
 * @description
 * @author ch
 * @date 2021/9/1
 * @edit
 */
class KotlinCallBackWrapper {

    fun setCallBack(kotlinCallBack: KotlinCallBack) {

    }

    fun setFunc(funcObject: () -> Unit) {
        funcObject()
    }

}