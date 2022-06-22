package cn.learn.android.myapplication

/**
 * @description
 * @author ch
 * @date 2021/9/16
 * @edit
 */
fun main() {
    val man = createMan(Man)
//    println("man name is ${man.name}")
}

fun createMan(manCreator: ManCreator): Man {
    return manCreator.createMan()
}

class Man private constructor(val name: String = "man") {

    companion object : ManCreator {
        override fun createMan(): Man = Man()
    }

}
interface ManCreator {
    fun createMan(): Man
}




