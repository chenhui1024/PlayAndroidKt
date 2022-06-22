package com.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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