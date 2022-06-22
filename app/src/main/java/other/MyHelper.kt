package other

/**
 * @description
 * @author ch
 * @date 2021/9/23
 * @edit
 */
class MyHelper(var age: Int? = 20) {

    private val name = "chen"

    fun testPrint(message: String): Unit {
        myPrintln("message is :$message")
    }

    fun getName(): String = name

}