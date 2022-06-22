package cn.learn.android.myapplication

fun main() {
//    Instance.print()
    click(Instance)
}

fun click(clickableObject: ClickableObject) {
    clickableObject.click()
}

object Instance : ClickableObject {

    fun print() {
    }

    override fun click() {
    }
}

interface ClickableObject {
    fun click()
}


