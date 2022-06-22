package com.example.playandroid_kt.rengwuxian.review

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelStore
import com.example.playandroid_kt.R
import kotlinx.coroutines.*
import other.OtherMainActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.text.MessageFormat
import java.util.ArrayList

/**
 * @description
 * @author ch
 * @date 2021/3/26
 * @edit
 */
class DemoActivity : AppCompatActivity() {

    private val str: String? = null

    private fun testFun(name: String = "111", age: Int, succ : Boolean = false): Unit {
        Log.d("chenhui", "name:$name,age:$age,succ:$succ")
    }

    private fun testFun2(name: String = "111", age: Int, rat: Int, succ : Boolean = false): Unit {
        Log.d("chenhui", "name:$name,age:$age,succ:$succ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        startActivity(Intent(this, OtherMainActivity::class.java))
        testFun(name = "chenhui", age = 29)
        testFun("fanxian", age = 28, succ = true)

        var str: String? = null
        val length: Int = str?.length ?: 0

//        var attributes = window.attributes
//        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
//        attributes.height = WindowManager.LayoutParams.MATCH_PARENT

        var intArray = IntArray(3) {
            Log.d("chenhui", "index:$it")
            it + 1
        }
        Log.d("chenhui", "result:${intArray.average()}")

        val array = Array(3) {
            Log.d("chenhui", "index1:$it")
            it + 1
        }
        Log.d("chenhui", "result1:${array.average()}")

        var list = List(10) {
            Log.d("chenhui", "index2:$it")
            it + 1
        }

        Log.d("chenhui", "result2:${list.average()}")

//        filter()

        var students: ArrayList<Student> = ArrayList(2)
        students.add(TopStudent("chenhui", 29))
        students.add(TopStudent("fanxian", 28, "women"))
        copyList(students, ArrayList(2))
    }

    private fun filter() {
        listOf(21, 40, 11, 33, 78)
            .filter {
                it%3 == 0
            }
            .forEach { i ->
                Log.d("filter", "result is $i")
            }
    }

    private fun testT(list: ArrayList<in Student>, student: Student) {
        list.add(student)
    }

    private fun copyList(listFrom: ArrayList<out Student>, listTo: ArrayList<in Student>) {

        val index = listFrom.size
        val range = 0 until index

        for (i in range) {
            Log.d("chenhui", "copy:$i")
            listTo.add(listFrom[i])
        }

        listTo.forEach { s ->
            val student: Student = s as Student
            Log.d("chenhui", "student name:${student.name}, age is ${student.age}, sex is ${student.sex}")
        }

    }

}