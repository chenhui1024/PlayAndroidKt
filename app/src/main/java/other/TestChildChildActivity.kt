package other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityTestChildChildBinding

/**
 * @description
 * @author ch
 * @date 2022/4/23
 * @edit
 */
class TestChildChildActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityTestChildChildBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_child_child)
//        setSupportActionBar(dataBinding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}