package jetpack.room.baseopr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityRoomOprBinding
import jetpack.room.AppDataBase
import jetpack.room.User
import jetpack.room.UserDao
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class RoomOprActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityRoomOprBinding

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_room_opr)
        dataBinding.lifecycleOwner = this

        userDao = AppDataBase.getDataBase().getUserDao()

        dataBinding.btnAdd.setOnClickListener {
            lifecycleScope.launch {
                repeat(10) {
                    val idNo = Random.nextLong(1000000)
                    val user = User(idNo.toString(), "姓名$idNo", age = idNo.mod(30), "地址$idNo")
                    userDao.insertUser(user)
                }
            }
        }

        dataBinding.btnModify.setOnClickListener {
        }

        dataBinding.btnQuery.setOnClickListener {
            dataBinding.tvResult.text = ""
            lifecycleScope.launch {
                userDao.getAllUsers().forEach {
                    dataBinding.tvResult.append(it.toString() + "\n")
                }
            }
        }

        dataBinding.btnDelete.setOnClickListener {
        }

        dataBinding.btnDeleteAll.setOnClickListener {
            lifecycleScope.launch { userDao.deleteAll() }
        }
    }

}