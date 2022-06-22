package other

import android.animation.AnimatorInflater
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.lesson3.Women
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.landi.cashierpaysdk.constant.InvokeKeyConst
import com.landi.cashierpaysdk.constant.TransNameConst
import com.landi.cashierpaysdk.sdk.CashierPayManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

/**
 * @description
 * @author ch
 * @date 2021/12/2
 * @edit
 */

private const val TAG = "CallMainActivity"

fun main() {

    val str = """$99""".trimMargin()

    println("str:$str")
}

class CallMainActivity : AppCompatActivity() {

    private var isInit = false
    private lateinit var parent: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        parent = findViewById(R.id.parent)

        GlobalScope.launch(Dispatchers.Main) {
            flow {
                (1..3).forEach {
                    Log.d("chenhui", "${Thread.currentThread().name},emit:$it")
                    emit(it)
                    delay(100)
                }
            }.flowOn(Dispatchers.IO).onEach {
                Log.d("chenhui", "${Thread.currentThread().name},onEach:$it")
            }.collect {
                Log.d("chenhui", "${Thread.currentThread().name},collect:$it")
            }
        }
    }

    private fun onClick(view: View) {
        val transaction: SupportTranstype = view.tag as? SupportTranstype ?: return
        onTranactionClick(transaction.transName!!, transaction.transType!!)
    }

    private val onTranactionClick: (String, String) -> Unit = { transName, transType ->
        val now = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val requestBundle = Bundle().apply {
            putString(InvokeKeyConst.TRANS_NAME1, transName)
            putString(InvokeKeyConst.AMOUNT1, "1")
            putString(InvokeKeyConst.ORDER_AMOUNT, "1")
            putBoolean(InvokeKeyConst.IS_DISCOUNTED, false)
            putString(InvokeKeyConst.ORDER_ID, "112233$now")
        }
        CashierPayManager.callPay(this, requestBundle, object : CashierPayManager.OnPayListener {
            override fun onSuccess(bundle: Bundle?) {
                Log.d(TAG, "onSuccess === > ")
                printBundle(bundle)
            }

            override fun onFailure(bundle: Bundle?) {
                Log.d(TAG, "onFailure === > ")
                printBundle(bundle)
            }

        })
    }

    private fun callRefund() {
        val requestBundle = Bundle().apply {
            putString(InvokeKeyConst.TRANS_NAME1, TransNameConst.REVOKE)
            putString(InvokeKeyConst.AMOUNT1, "1")
            putString(InvokeKeyConst.TRANS_ID, "L042021121014230938464408")
        }
        CashierPayManager.callPay(this, requestBundle, object : CashierPayManager.OnPayListener {
            override fun onSuccess(bundle: Bundle?) {
                Log.d(TAG, "onSuccess === > ")
                printBundle(bundle)
            }

            override fun onFailure(bundle: Bundle?) {
                Log.d(TAG, "onFailure === > ")
                printBundle(bundle)
            }

        })
    }

    override fun onStart() {
        super.onStart()
        isInit.takeUnless {
            it
        }?.let {
            isInit = true
            CashierPayManager.init(this, Bundle(), object : CashierPayManager.OnPayListener {
                override fun onSuccess(bundle: Bundle?) {
                    Log.d(TAG, "onSuccess === > ")
                    printBundle(bundle)
                    val transactions = parseTransactionList(bundle?.getString(InvokeKeyConst.SUPPORT_TRANSTYPE))
                    runOnUiThread {
                        updateViewByTransactions(transactions)
                    }
                }

                override fun onFailure(bundle: Bundle?) {
                    Log.d(TAG, "onFailure === > ")
                    printBundle(bundle)
                }
            })
        }
    }

    fun parseTransactionList(supportTranstype: String?): List<SupportTranstype> {
        return supportTranstype?.let {
            val type = object : TypeToken<List<SupportTranstype>>(){}.type
            return Gson().fromJson(it, type)
        } ?: emptyList()
    }

    fun updateViewByTransactions(supportTransaction: List<SupportTranstype>) {
        parent.removeAllViews()
        for ((index, transaction) in supportTransaction.withIndex()) {
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .apply {
                    if (index == 0) {
                        topMargin = 10
                    }
                    leftMargin = 10
                    rightMargin = 10
                }

            val button = Button(parent.context).apply {
                text = transaction.showName
                tag = transaction
                setOnClickListener(::onClick)
            }
            parent.addView(button, params)

            if (index == supportTransaction.size - 1) {
                val refundButton = Button(parent.context).apply {
                    text = "退款"
                    setOnClickListener {
                        callRefund()
                    }
                }
                parent.addView(refundButton, params)
            }
        }
    }

    fun printBundle(bundle: Bundle?) {
        bundle?.run {
            keySet()?.forEach { key ->
                Log.d(TAG, "$key:${get(key)}")
            }
        }
    }

}

data class SupportTranstype(
    val showName: String? = null,
    val transName: String? = null,
    val transType: String? = null,
    val isShow: String? = null,
    val erpCode: String? = null,
    val subScreenCls: String? = null
)
