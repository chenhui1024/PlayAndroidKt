package com.example.playandroid_kt.rengwuxian.review.launcher

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.review.Student
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

/**
 * @description
 * @author ch
 * @date 2021/3/30
 * @edit
 */
class LauncherMainActivity : AppCompatActivity() {

    private lateinit var imvFirstPart: ImageView
    private lateinit var imvLastPart: ImageView

    private var method: () -> Unit = fun (){}

    private fun testFunc(function: () -> Unit) {
        method = function
        function.invoke()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_main)

        testFunc {
            Log.d("chenhui", "测试成功 === > ")
        }

        testFunc(fun (): Unit {
            Log.d("chenhui", "测试成功 === > ")
        })

        imvFirstPart = findViewById(R.id.imv_first_part)
        imvLastPart = findViewById(R.id.imv_last_part)

        Thread {
            Log.d("chenhui", "thread name: ${Thread.currentThread().name}")
        }.start()


//        GlobalScope.launch {
//        }

//        testInterfaceLamda(object : ITestInterface {
//            override fun onCallBack() {
//                Log.d("chenhui", "running === > ")
//            }
//        })

        testInterfaceLamda {
            Log.d("chenhui", "running === > ")
        }

//        runBlocking {
//            SystemClock.sleep(3000)
//            Log.d("chenhui", "wait for ==== > ")
//        }
//        Log.d("chenhui", "onCreate finished === > ")

//        val imvNetImage: ImageView = findViewById(R.id.imv_net_image)
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.d("chenhui", "start getImage === > ")
//            getImage()?.apply {
//                Log.d("chenhui", "setImage === > ")
//                imvNetImage.setImageBitmap(this)
//                imvFirstPart.setImageBitmap(getFirstPart(this))
//                imvLastPart.setImageBitmap(getLastPart(this))
//            }
//            Log.d("chenhui", "end getImage === > ")
//        }
//        Log.d("chenhui", "onCreate finished === > ")

        downloadImage()
    }

    private fun downloadImage() {

        val diloag = Dialog(this)
        diloag.window?.requestFeature(Window.FEATURE_NO_TITLE)
        diloag.setContentView(R.layout.dialog_loading)
        diloag.show()

        val attributeSet = diloag.window?.attributes
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        attributeSet?.width = displayMetrics.widthPixels / 2
        attributeSet?.height = WindowManager.LayoutParams.WRAP_CONTENT
        diloag.window?.attributes = attributeSet

        CoroutineScope(Dispatchers.Main).launch {
            val imvNetImage: ImageView = findViewById(R.id.imv_net_image)
            val bitmap = withContext(Dispatchers.IO) {
                SystemClock.sleep(3000)
                val url = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3355464299,584008140&fm=26&gp=0.jpg"
                val URL = URL(url)
                val connection = URL.openConnection() as HttpURLConnection

                connection.connect()
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    return@withContext BitmapFactory.decodeStream(connection.inputStream)
                }
                null
            }

            diloag.dismiss()
            if (bitmap == null) {
                Toast.makeText(this@LauncherMainActivity, "下载失败", Toast.LENGTH_SHORT).show()
            } else {
                imvNetImage.setImageBitmap(bitmap)
                Toast.makeText(this@LauncherMainActivity, "下载成功", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun testInterfaceLamda(callBack: () -> Unit) {
        callBack.invoke()
    }

    interface ITestInterface {
        fun onCallBack()
    }

    private suspend fun getImage(): Bitmap? = withContext(Dispatchers.IO) {
        SystemClock.sleep(3000)
        val url = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3355464299,584008140&fm=26&gp=0.jpg"
        var response = OkHttpClient().newCall(Request.Builder().url(url).get().build()).execute()
        if (response.isSuccessful) {
            return@withContext BitmapFactory.decodeStream(response.body?.byteStream())
        }
        null
    }

    private fun getFirstPart(bitmap: Bitmap): Bitmap {
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width / 2, bitmap.height / 2)
    }

    private fun getLastPart(bitmap: Bitmap): Bitmap {
        return Bitmap.createBitmap(bitmap, bitmap.width / 3 * 2, bitmap.height / 3 * 2,
            bitmap.width / 3, bitmap.height / 3)
    }

}