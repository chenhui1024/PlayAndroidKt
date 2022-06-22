package com.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityTestChildBinding
import other.TestChildChildActivity

/**
 * @description
 * @author ch
 * @date 2022/4/23
 * @edit
 */
class TestChildActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityTestChildBinding

//    private fun <T : TextView> addView(views: ArrayList<in T>) {
//        views.add()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_child)

        setSupportActionBar(dataBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataBinding.btnJump.setOnClickListener {
            startActivity(Intent(this, TestChildChildActivity::class.java))
        }
    }
}