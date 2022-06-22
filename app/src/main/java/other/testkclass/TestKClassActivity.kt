package other.testkclass

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityTestDrawableBinding
import com.example.playandroid_kt.databinding.LayoutTmpBinding
import com.example.playandroid_kt.databinding.LayoutTmpBindingImpl
import kotlin.reflect.KType
import kotlin.reflect.full.*

/**
 * @description
 * @author ch
 * @date 2022/5/5
 * @edit
 */
class TestKClassActivity : AppCompatActivity() {

    private fun testMembers() {
        val instance = Child()
        val cls = instance::class
        Log.d("chenhui", " ==== memberProperties ==== ")
        cls.memberProperties.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== declaredMemberProperties ==== ")
        cls.declaredMemberProperties.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== members ==== ")
        cls.members.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== declaredMembers ==== ")
        cls.declaredMembers.forEach {
            Log.d("chenhui", "$it")
        }

        Log.d("chenhui", " ==== functions ==== ")
        cls.functions.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== declaredFunctions ==== ")
        cls.declaredFunctions.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== memberFunctions ==== ")
        cls.memberFunctions.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== declaredMemberFunctions ==== ")
        cls.declaredMemberFunctions.forEach {
            Log.d("chenhui", "$it")
        }
        Log.d("chenhui", " ==== declaredMemberExtensionFunctions ==== ")
        cls.declaredMemberExtensionFunctions.forEach {
            Log.d("chenhui", "$it")
        }
    }

    private fun testValueGet() {
        val instance = Child()
        val cls = instance::class
        //        val value = cls.memberProperties.findLast { it.name == "childPubValue" }?.getter?.call(instance)
        val pro = cls.memberProperties.first { it.name == "childPubValue"  }
        //1.方式1
//        val strPro:KProperty1<Child, String>? = pro as? KProperty1<Child, String>
//        val result = strPro?.get(instance)
        //2.方式2
        val result = pro.call(instance)
        Log.d("chenhui", "result:$result")
    }

    private fun testReturnType() {
        val method = Child::childPubFunc
        val ktype = method.returnType
        Log.d("chenhui", " ==== arguments ==== ")
        ktype.arguments.forEach {
            Log.d("chenhui", "$it")
        }
    }

    interface Temp{}
    abstract class SuperType<T> {}
    open class ChildType : SuperType<String>() {}
    class SubChildType : ChildType() {}

    /**
     *   获取父类的泛型实参
     */
    private fun testSuperType() {
//        val cls = ChildType::class
//        val types = cls.supertypes.first().arguments.first()
//        Log.d("chenhui", "types:$types")

        val cls = SubChildType::class
        val types: Collection<KType> = cls.allSupertypes
        Log.d("chenhui", "types:$types")
        val superType = types.first { it.arguments.isNotEmpty() }.arguments.first()
        Log.d("chenhui", "superType:$superType")
        val type = superType.type
        Log.d("chenhui", "args:$type")
    }

//    private lateinit var dataBinding: ActivityTestDrawableBinding
    private lateinit var dataBinding: LayoutTmpBindingImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.layout_tmp)
//        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_drawable)
//        testSuperType()
//        testReturnType()
    }

}