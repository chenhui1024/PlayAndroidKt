package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/11/27
 * @edit
 */
class Singleton private constructor() {

    companion object {
        init {
            println("Singleton init === > ")
        }

        fun getInstance() = SingltonHolder.instance
    }

    init {
        println("Singleton instance init === > ")
    }

    private class SingltonHolder {
        companion object {
            val instance: Singleton = Singleton()

            init {
                println("SingltonHolder init === > ")
            }

        }
    }

}