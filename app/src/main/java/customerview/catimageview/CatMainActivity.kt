package customerview.catimageview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityCatMainBinding

/**
 * @description
 * @author ch
 * @date 2022/2/12
 * @edit
 */
class CatMainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityCatMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_cat_main)
    }

}