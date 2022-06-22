package com.example.playandroid_kt.rengwuxian.lesson7

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.review.Student

/**
 * @description
 * @author ch
 * @date 2020/8/20
 * @edit
 */
class Lesson7Activity : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson7)

        etInput = findViewById(R.id.et_input)
        btnOk = findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            val input: String = etInput.text.toString().trim()
            if (TextUtils.isEmpty(input)) {
                return@setOnClickListener
            }
            Log.d("chenhui", "input is $input")
        }

        test()
    }


    fun test() {
        listOf(1, 2).forEach({item ->
            Log.d("chenhui", "listof == > $item")
        })

        listOf(3, 4).forEach() { item ->
            Log.d("chenhui", "listof == > $item")
        }

        listOf(5, 6).forEach { item ->
            Log.d("chenhui", "listof == > $item")
        }

        Log.d("chenhui", "test() start === > ")
        listOf(1, 2, 3, 4, 5).forEach {
            /**
             * 这里的返回，会直接将test（）函数返回，下面的 test() finished 不会被执行
             * (注意, 这种非局部的返回(non-local return), 仅对传递给 内联函数(inline function) 的 Lambda 表达式有效.)
             * 如果需要从 Lambda 表达式返回, 我们必须对它标记一个标签, 然后使用这个标签来指明 return 的目标:
             */
//            if (it >= 3) {
//                return
//            }
            if (it >= 3) {
                return@forEach //这里的返回只是返回当前的foeEach，下面的 test() finished 会被执行
            }
            Log.d("chenhui", "it === > $it")
        }
        Log.d("chenhui", "test() finished === > ")
    }

}