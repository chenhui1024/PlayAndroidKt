package customerview.viewdraghelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityViewDragHelperBinding

/**
 * @description
 * @author ch
 * @date 2022/3/1
 * @edit
 */
class ViewDragHelperMainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityViewDragHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_drag_helper)
    }

}