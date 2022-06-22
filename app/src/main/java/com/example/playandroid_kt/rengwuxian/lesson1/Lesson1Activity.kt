package com.example.playandroid_kt.rengwuxian.lesson1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.playandroid_kt.R
import com.example.playandroid_kt.rengwuxian.lesson2.Lesson2Activity
import kotlinx.android.synthetic.main.activity_lesson1.*

class Lesson1Activity : AppCompatActivity() {

    lateinit var btnTest: Button
    var name: String? = "cc"
    val str = "chenhui"
    var number: Int = 999
    var c: Char = 'a'
    var b: Boolean = true
    var itArray: IntArray = intArrayOf(1, 2, 3)
    var f: FloatArray = floatArrayOf(0.01f, 0.02f, 0.03f)
    var apple: Apple? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)

        btnTest = findViewById(R.id.btn_test)
        btnTest.setOnClickListener {

            // (it as? Button) 之后是一个可空类型的对象，所以，需要使用 '?.' 来调用
            Log.d("chenhui", "name:" + (it as? Button)?.text.toString())

        }

        var food = createFood("苹果", "中国")
        Log.d("chenhui", "food:" + food.foodName + "|" + food.produceArea)
//        nonAble(name)
        print("输出这段话")

        for (i in itArray) {
            Log.d("chenhui", "intArray:[$i]")
        }

        apple = Apple()
        Log.d("chenhui", "foodName:${apple?.foodName},productArea:${apple!!.produceArea}")

        apple = BigApple()
        if (apple is BigApple) {
            Log.d("chenhui", "bigApple weight:${(apple as BigApple).getWeight()}")
        }

    }

    fun print(str: String) {

        Log.d("chenhui", "print str:$str")

    }

    fun createFood(foodName: String, productArea: String): Food {

        var food: Food = Food(foodName, productArea)

//        food.foodName = foodName
//        food.produceArea = productArea

        return food
    }

}
