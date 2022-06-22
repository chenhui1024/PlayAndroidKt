package other.testkclass

/**
 * @author ch
 * @description
 * @date 2022/5/5
 * @edit
 */
class Child : Parent() {
    var childPubValue: String? = "2233"
    private val childPriValue: String? = null
    fun childPubFunc(pre: String): Map<String, Int> { return mapOf(pre to 2) }
    private fun childPriFunc() {}

    fun String.externalPubFunc() {
    }
}

