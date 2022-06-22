package com.example.playandroid_kt.rengwuxian.lesson5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.lesson4.Apple
import kotlinx.coroutines.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.math.pow

/**
 * @description
 * @author ch
 * @date 2020/7/5
 * @edit
 *
 * 强调一下，协程的非阻塞式挂起，本质上是切线程，这个和java的切线程是一样的，为什么叫非阻塞式挂起呢，相比于java的单纯切线程，协程的最大好处是等待结束之后，自动切回来。
 *
 * 协程：就是java的切线程
 * 挂起：切出去之后，能够自动切回来
 *
 */
class Lesson5Activity : AppCompatActivity() {

    private lateinit var imvBitmap: ImageView
    private lateinit var imvBitmapFirst: ImageView
    private lateinit var imvBitmapNinth: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson5)

        imvBitmap = findViewById(R.id.imv_bitmap)
        imvBitmapFirst = findViewById(R.id.imv_bitmap_first)
        imvBitmapNinth = findViewById(R.id.imv_bitmap_ninth)

        testThread()

        var a: Int? = 10
        var b = a?.takeIf {
            a > 20
        }
        Log.d("chenhui", "b:$b")

        var c = a?.takeIf {
            a > 5
        }
        Log.d("chenhui", "c:$c")

        /**
         *  Dispatchers.Main：Android 中的主线程
        Dispatchers.IO：针对磁盘和网络 IO 进行了优化，适合 IO 密集型的任务，比如：读写文件，操作数据库以及网络请求
        Dispatchers.Default：适合 CPU 密集型的任务，比如计算
         */

        //不会阻塞线程,但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，且不能取消
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
//            withContext(Dispatchers.IO) {
//                SystemClock.sleep(3000)
//                Log.d("chenhui", "Dispatchers.IO:${Thread.currentThread().name}")
//                SystemClock.sleep(3000)
//            }
//            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
//        }

        //比较推荐的使用方法.
        // 我们可以通过 context 参数去管理和控制协程的生命周期（这里的 context 和 Android 里的不是一个东西，是一个更通用的概念，会有一个 Android 平台的封装来配合使用）
        //方式1
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
//            val bitmap = withContext(Dispatchers.IO) {
//                Log.d("chenhui", "Dispatchers.IO:${Thread.currentThread().name}")
//                getImage()
//            }
//            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
//            imvBitmap.setImageBitmap(bitmap)
//        }

        //方式2
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
//            val bitmap = getImage2()
//            imvBitmap.setImageBitmap(bitmap)
//        }

        //使用okhttp
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("chenhui", "Dispatchers.Main:${Thread.currentThread().name}")
            val bitmap = getImageWithOkHttp()
            //原图
            imvBitmap.setImageBitmap(bitmap)
            //第一部分
            val part1Bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width/2, bitmap.height/2)
            imvBitmapFirst.setImageBitmap(part1Bitmap)

            //最后一部分
            Log.d("chenhui", "bitmap width:${bitmap.width}, height:${bitmap.height}")
            Log.d("chenhui", "bitmap width:${bitmap.width/3*2}, height:${bitmap.height/3*2}")
            val part9Bitmap = Bitmap.createBitmap(bitmap, bitmap.width/3*2, bitmap.height/3*2, bitmap.width/3, bitmap.height/3)
            imvBitmapNinth.setImageBitmap(part9Bitmap)
        }
    }

    //suspend加入是为了线程切换之后能够切回来。同时，suspend并不会造成线程切换，是因为内部withContext的里面调用了一个挂起函数。
    //如果我们想自定义一个挂起函数，也要调用到这个挂起函数
    //那么suspend的作用到底是什么？：提醒！！！是函数的创建者，提示调用者，我是一个耗时操作，因此我采用了挂起操作，你要在协程里面调用我
    //同时，加入了suspend限制之后，该函数只能在协程里面被调用。而且运行完毕之后，再切回来这个动作是协程完成的，所以也同样限制了该函数在协程里面调用
    private suspend fun getImage2(): Bitmap {
        return withContext(Dispatchers.IO) {
            getImage()
        }
    }

    private suspend fun getImageWithOkHttp(): Bitmap {
        return withContext(Dispatchers.IO) {
            val path = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=236105504,2876063204&fm=26&gp=0.jpg"
            OkHttpClient().newCall(Request.Builder().url(path).get().build())
                .execute().body?.byteStream().use {
                    BitmapFactory.decodeStream(it)
                }
        }
    }

    private fun getImage(): Bitmap {
        val path = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=236105504,2876063204&fm=26&gp=0.jpg"
        val url: URL = URL(path)
        val httpURLConnection = url.openConnection()
        httpURLConnection.doInput = true
        httpURLConnection.connect()
        return BitmapFactory.decodeStream(httpURLConnection.getInputStream())
    }

    fun testThread() {
        //启动一个线程，方法1
//        Thread({
//            Log.d("chenhui", "testThread start !!!")
//        }).start()

        //方法2
        Thread {
            Log.d("chenhui", "testThread start !!!")
            Log.d("chenhui", "currentThread Name:${Thread.currentThread().name}")
        }.start()

        //方法3
        Executors.newCachedThreadPool().execute {
            Log.d("chenhui", "testThread start with executors !!!")
            Log.d("chenhui", "currentThread Name:${Thread.currentThread().name}")
        }
    }

    fun t() {
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap = suspendLoadImage()
            bitmap?.let {
                val bitmap1 = suspendCropBitmap(it, 2, 2, 0, 0)
                val bitmap2 = suspendCropBitmap(it, 3, 3, 2, 2)
//                iv1.setImageBitmap(bitmap1)
//                iv2.setImageBitmap(bitmap2)
            }
        }
    }

    private suspend fun suspendLoadImage() = withContext(Dispatchers.IO) {
        val imgUrl = "http://oss.tuyuing.com/TUYU/trend/20190930/trend257401569854904487.jpeg"
        val connection = URL(imgUrl).openConnection() as HttpURLConnection
        if (connection.responseCode != 200) {
            return@withContext null
        }
        return@withContext BitmapFactory.decodeStream(connection.inputStream)
    }


    private suspend fun suspendCropBitmap(source: Bitmap, sliceX: Int, sliceY: Int, posX: Int, posY: Int) =
        withContext(Dispatchers.Default) {
            val cropWidth = source.width / sliceX
            val cropHeight = source.height / sliceY
            Bitmap.createBitmap(source, cropWidth * posX, cropHeight * posY, source.width / sliceX,
                source.height / sliceY)
        }

}