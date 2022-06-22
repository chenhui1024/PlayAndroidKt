package other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.playandroid_kt.R
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

class OtherMainActivity : AppCompatActivity() {

    var length: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_main)
        test()

//        val quote = ::length
//        other.println("length:${quote.get()}")
//        other.println("length:${quote.set(12)}")
//        other.println("length:${quote.get()}")
//        val innerCls = InnerCls(10)
//        val ageQuote = InnerCls::age
//        ageQuote.set(innerCls, 11)
//        ageQuote.get(innerCls)
    }

    private fun test() {
        val helper = MyHelper(28)

        val kCls = helper::class
        val members = kCls.members
        for (member in members) {
            myPrintln(member.name)
            if ("age" == member.name) {
                val age: KProperty<Int> = member as KProperty<Int>
                other.myPrintln("getAge:${age.getter.call(helper)}")
            }
            if (member.name == "getName") {
                other.myPrintln("getName is :${member is KFunction<*>}")
//                val name: String = member.call(helper) as? String ?: ""
//                other.println("name is :$name")
                val getName: KFunction<String> = member as KFunction<String>
                val name = getName.call(helper)
                other.myPrintln("name:$name")
            }
        }
        myPrintln("isValue:${kCls.isValue}")
        myPrintln("isData:${kCls.isData}")
        myPrintln("nestedClasses:${kCls.nestedClasses}")
        myPrintln("name:${kCls.qualifiedName}")
    }

    class InnerCls(var age: Int) {
    }

}

fun myPrintln(message: String?) {
    message?.let {
        Log.d("chenhui", message)
    }
}