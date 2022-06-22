package other

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @description
 * @author ch
 * @date 2021/12/9
 * @edit
 */
class YFKImportActivity : AppCompatActivity() {

    private val TAG = "YFKImportActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yfkimport)

        findViewById<Button>(R.id.btn_import).setOnClickListener {
            import()
        }

        GlobalScope.launch(Dispatchers.Main) {
                flow {
                    (1..3).forEach {
                        println("${Thread.currentThread().name},emit:$it")
                        emit(it)
                        delay(100)
                    }
                }.onEach{
                    println("${Thread.currentThread().name} 重要的线程")
                }.flowOn(Dispatchers.IO).onEach {
                    println("${Thread.currentThread().name},onEach:$it")
                }.collect {
                    println("${Thread.currentThread().name},collect:$it")
                }
        }
    }

    fun encode(password: String): String {
        try {
            val instance:MessageDigest = MessageDigest.getInstance("MD5")//获取md5加密对象
            val digest:ByteArray = instance.digest(password.toByteArray())//对字符串加密，返回字节数组
            val sb = StringBuffer()
            for (b in digest) {
                val i :Int = b.toInt() and 0xff//获取低八位有效值
                var hexString = Integer.toHexString(i)//将整数转化为16进制
                if (hexString.length < 2) {
                    hexString = "0" + hexString//如果是一位的话，补0
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun import() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val filePath = Environment.getExternalStorageDirectory().absolutePath + "/import"
                val file = File(filePath)
                Log.d(TAG, "path:$filePath is exit:${file.exists()}")
                file.takeIf {
                    it.exists()
                }?.let { it ->
                    try {
                        val content = StringBuilder()
                        val list = arrayListOf<SaleEntity>()
                        val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(it)))
                        bufferedReader.use {
                            while (true) {
                                val line: String? = bufferedReader.readLine()
                                line?.let { lineText ->
                                    Log.d(TAG, "line:$lineText")
                                    content.append(lineText + "\n")
                                    if (lineText.length > 100) {
                                        Body(lineText.substring(34 + 4)).saleEntity?.takeIf {
                                            it.saleType == "00"
                                        }?.let { entity ->
                                            list.add(entity)
                                            Log.d(TAG, "type:${entity.saleType}")
                                            Log.d(TAG, "amout:${entity.oldPrice}")
//                                            Log.d(TAG, "saleTime:${entity.saleTime}")
                                        }
                                    }
                                } ?: break
                            }
//                            val totalAmount = list.map(SaleEntity::price).sumOf { it?.toInt(16) ?: 0 }
                            val totalAmount = list.map(SaleEntity::price).sumOf { it?.toInt() ?: 0 }
                            Log.d(TAG, "totalAmount:$totalAmount, ,size:${list.size}")
                            Log.d(TAG, "MD5:${encode(content.toString()).uppercase()}")
                        }
                        Log.d(TAG, "finished !")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }



    class Body(private val bodyString: String) {

        companion object {
            private val SALE_TYPE = Part(length = 2)
            private val TRACE = SALE_TYPE.nextPart(8)
            private val CARD_NO = TRACE.nextPart(8)
            private val CARD_UUID = CARD_NO.nextPart(8)
            private val SALE_TIME = CARD_UUID.nextPart(14)
            private val OLD_PRICE = SALE_TIME.nextPart(8)
            private val PRICE = OLD_PRICE.nextPart(8)
            private val SALE_COUNT = PRICE.nextPart(8)
            private val LAST_BALANCE = SALE_COUNT.nextPart(8)
            private val PUBLIC_BLOCK0 = LAST_BALANCE.nextPart(32)
            private val WALLET_BLOCK0 = PUBLIC_BLOCK0.nextPart(32)
            private val WALLET_BLOCK1 = WALLET_BLOCK0.nextPart(32)
        }

        var saleEntity: SaleEntity? = null

        init {
            saleEntity = SaleEntity(
                getPartValue(SALE_TYPE),
                getPartValue(TRACE),
                getPartValue(CARD_NO),
                getPartValue(CARD_UUID),
                getPartValue(SALE_TIME),
                getPartValue(OLD_PRICE),
                getPartValue(PRICE),
                getPartValue(SALE_COUNT),
                getPartValue(LAST_BALANCE),
                getPartValue(PUBLIC_BLOCK0),
                getPartValue(WALLET_BLOCK0),
                getPartValue(WALLET_BLOCK1),
            )
        }

        private fun getPartValue(part: Part): String? {
            return bodyString.takeIf {
                it.length >= part.offset + part.length
            }?.substring(part.offset, part.offset + part.length)
        }

        class Part(val offset: Int = 0, val length: Int) {
            fun nextPart(length: Int): Part {
                return Part(this.offset + this.length, length)
            }
            companion object {
                fun nextPart(lastPart: Part, length: Int) = lastPart.nextPart(length)
            }
        }

    }

    data class SaleEntity(
        val saleType: String? = null,
        val traceNo: String? = null,
        val cardNo: String? = null,
        val cardUUID: String? = null,
        val saleTime: String? = null,
        val oldPrice: String? = null,
        val price: String? = null,
        val saleCount: String? = null,
        val lastBalance: String? = null,
        val publicBlock0: String? = null,
        val walletBlock0: String? = null,
        val walletBlock1: String? = null
    )

}



