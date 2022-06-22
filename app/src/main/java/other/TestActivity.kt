package other

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import com.example.TestChildActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityTestDrawableBinding
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

/**
 * @description
 * @author ch
 * @date 2022/4/19
 * @edit
 */
class TestActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityTestDrawableBinding

//    val users = listOf(
//        User("1", 20),
//        User("2", 21),
//        User("3", 22),
//        User("4", 23),
//        User("5", 24),
//        User("6", 25),
//    )

    val users = object : ObservableArrayList<User>() {
        init {
            add(User("1", 20))
            add(User("2", 21))
            add(User("3", 22))
            add(User("4", 23))
            add(User("5", 24))
        }
    }

    open class TestBase {
        fun pubFunc() {}

        var pubValue: String? = null
        private var priValue: String? = null
    }

    open class GenTest<T>(public val data: T) : TestBase() {

        open val name: String = "222"

        private var valuePri: String? = null

        fun printOut() {}

        private fun printOutInternal() {}

    }

    class GenTestImpl(data: String) : GenTest<String>(data) {

//        val age: Int = 0

    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

        }
    }

    fun calcWithPub() {
        val data = "acctno=6236681820006473426&appid=00000051&signintime=202205113094141&termno=00000001"

        //获取publicKey
        //联迪公钥
        val key = Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo0hZX6k0P3xnD55ug1Al9oLnXiHPRwLjOia9qTOapCVAMZxDRl8BEBZijnGxI353L+C4Dsk/252DQX2RiwM5q1HsxPrhwHCOU22tXBEzG7JssCoYBEWreAOvxYsovRwcF4uJVh/e/0aSw8iw5kCbz7fltxBFmZR8RXVVwah0c+5S0VTeksWzkGDs2FCjeT8IpMXbBTGeWe/py7eMZq5GynjM4Wz9scLTU0Uvb2cK4Rn4kdSAU+eemkmBGcIRbxHH1adTZ9wca1sw3mQ4MOYsEQpmHQ/dcYAzed05tALfkEDqOMgkrc4vCjkjYWF19+cAxg1ONVl2oH0oeB4aG/V1/wIDAQAB", Base64.DEFAULT)
        val pubKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(key))

        //私钥加密之后的数据，也就是需要解密的数据
        val base64Src = "ML+tp9L7sltXvuD4aSvz+CFaZf/hSpxrfMgVnowEs+zRqdMxSLRJnq3RbXp2bhAwwhbUInYu" +
                "anE863HC1mZjPC4TOTlFF0M7rwx56V9VKLsqRXBefusRbJ9qeg0B5lWDa9fo+1f/XlOOHNo/" +
                "bLmB50+SzWC3HkxnrzBWvD6m0mBGkSOZx1egLNDAg3nbM7+sbICVOfC+U450xuwH/Eb603mx" +
                "8Xv6zgiaCFbmzt16/fkyCqaoz80PUUghvSHb0ZZW8vU4wwstYXzClsNg6iKX0BzwW3PFqdUp" +
                "9SJuEOEqkHbfzCnJwFLmZX3HcfHfKV7iYguIysPVZezGzhNDC8ocEg=="
        val dataByte = Base64.decode(base64Src, Base64.DEFAULT)

        val signature = Signature.getInstance("SHA1WithRSA")
        signature.initVerify(pubKey)
        signature.update(data.toByteArray())

        Log.d("chenhui", "验签结果:${signature.verify(dataByte)}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calc()
//        calcWithPub()
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_drawable)

        setSupportActionBar(dataBinding.toolbar)

        dataBinding.btnJump.setOnClickListener {
            startActivity(Intent(this, TestChildActivity::class.java))
        }
    }

    fun calc() {
//        val data = "acctno=6236681820006473426&appid=00000051&signintime=202205113094141&termno=00000001"
        val data = "acctno=6230631234560&appid=00208774&signintime=20220513142200&termno=6543210"
        val bytePrivateKey = Base64.decode("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCjSFlfqTQ/fGcPnm6DUCX2gudeIc9HAuM6Jr2pM5qkJUAxnENGXwEQFmKOcbEjfncv4LgOyT/bnYNBfZGLAzmrUezE+uHAcI5Tba1cETMbsmywKhgERat4A6/Fiyi9HBwXi4lWH97/RpLDyLDmQJvPt+W3EEWZlHxFdVXBqHRz7lLRVN6SxbOQYOzYUKN5PwikxdsFMZ5Z7+nLt4xmrkbKeMzhbP2xwtNTRS9vZwrhGfiR1IBT556aSYEZwhFvEcfVp1Nn3BxrWzDeZDgw5iwRCmYdD91xgDN53Tm0At+QQOo4yCStzi8KOSNhYXX35wDGDU41WXagfSh4Hhob9XX/AgMBAAECggEAAwn9dvcAzpzMlHvXEkhhmrmg/+SgweMmUN2mZEKznsrwCAqSg087lZv1DMBUEwcLxb12/b92C7HsXs/l75Q0AsyCcTHlwGvX4WqGR7bXkjtJeUCS3r+1NfdLmYwXgyEbB2maHIzq3PF2p5zf9uWTnUVyScG+OBsbnQPIgJp0CxUy10+/Ezf/j90LaCYeOUJ9ugnU83u7zHeD/uRP2XNFLaU6JWRcscDz4kzPyNaL+A5EWYM8ADWF6w2PsiZL8GwHx0k52FSU9MyXOqdXEHoSYOixUFZDytpOPX87/G+R8G3j4Q8ZxGaSChkDTwxJBUaR74ff87N1DXlKszSCs5e04QKBgQDbc5ka8GEPDjB1OA1ZMVfBTct3hk7rGejPS2fXxTWOUm1Ghwc9K55zBXKSykKoZf8dW5sGVN3OA08RKwuNmQfh0n/wIbuF7fw3WHjcihCKlMo6+EhK4qqb/CYiNfbP0XzfGOp6gy2v+TgLvdcIFLN949JPUjR5QqAmUaIs/fkJZwKBgQC+efdtYiIEwNZ3v6g7QP2lENUyS6wkxfpp+zuukiwZ5EFL4MwDPV3RX4oSJ6k6nkSDTCOEvyZgrQH5t6JZMoeYLQLdj35jXRlKXeCM5NvXPZOa6DzXfRdPPhIBgGEBVX3JlZwOo0HSFUK+i8smP9GAk8b5SVBkYf0PMrqsx9oXqQKBgEU5TMWLqAI4xp5jRQqqnvTyFkmoy0IsFR02uxX8Nm2joKbLt0Pbe4rq3/5f4PD/456eI+O2Z5UxxAI1YtqSuELKxOK5sU6JCLNorA9dWeRtFPHPGfbNU2YK/vhxr15UxaAWWvi0YngNFFnX03nQFeKTN+cflj/d/Wi4/YBVyPXnAoGAR96NVWBppZF9WsGeIH1tNxMa3ldfJKDu035QDpZovXw6hv7S+4X0GEQMPL1qRQvingfkfvoA6QlebXK0dyNxtiGFq0OxVJyUJlM1nvPbX2OEhjaKfmRcnzG0aYyfDXnMdHtW3s8KztHm1S66ADykN/eKZBUdmELA0TM67GacC8ECgYAuEI9Kjov68DiEvir7dAx1sHY/WM7wobbuEgx1/KkfIn7CoB5ZkA9SYCtHmYRZhgmWTVGUOzQJ4URBMs1q/avG04pZGxBp9moSGs8P4ZHt/JsstldhTP7mIqZSqVrku24RGsU7p0T9I8Ql/abkdPULCK2TVV0WJfEQSutF5DduPQ==", Base64.DEFAULT)

        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(bytePrivateKey))
        var signature = Signature.getInstance("SHA1WithRSA")
        signature.initSign(privateKey)
        signature.update(data.toByteArray())
        val result = signature.sign()
        Log.d("chenhui", "私钥加签的长度：${result.size}")

        val resultBase64 = Base64.encodeToString(result, Base64.DEFAULT)
        Log.d("chenhui", "私钥加签BASE64结果:$resultBase64")

        Log.d("chenhui", "========== 验签 ==========")
        val key = Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo0hZX6k0P3xnD55ug1Al9oLnXiHPRwLjOia9qTOapCVAMZxDRl8BEBZijnGxI353L+C4Dsk/252DQX2RiwM5q1HsxPrhwHCOU22tXBEzG7JssCoYBEWreAOvxYsovRwcF4uJVh/e/0aSw8iw5kCbz7fltxBFmZR8RXVVwah0c+5S0VTeksWzkGDs2FCjeT8IpMXbBTGeWe/py7eMZq5GynjM4Wz9scLTU0Uvb2cK4Rn4kdSAU+eemkmBGcIRbxHH1adTZ9wca1sw3mQ4MOYsEQpmHQ/dcYAzed05tALfkEDqOMgkrc4vCjkjYWF19+cAxg1ONVl2oH0oeB4aG/V1/wIDAQAB", Base64.DEFAULT)
        val publicKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(key))

        //私钥加密之后的数据，也就是需要解密的数据
        val dataByte = Base64.decode(resultBase64, Base64.DEFAULT)
        Log.d("chenhui", "base64解码之后的长度：${dataByte.size}")

        signature = Signature.getInstance("SHA1WithRSA")
        signature.initVerify(publicKey)
        signature.update(data.toByteArray())

        Log.d("chenhui", "验签结果:${signature.verify(result)}")
    }

    fun onBindItem(dataBinding: ViewDataBinding, user: User, pos: Int) {
        Log.d("chenhui", "onBindItem === > $user")
    }

    data class User(
        val name: String,
        val age: Int
    )

}