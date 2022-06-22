package other.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.playandroid_kt.R

/**
 * @description
 * @author ch
 * @date 2021/11/26
 * @edit
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_main)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        moveTaskToBack(true)
    }

}