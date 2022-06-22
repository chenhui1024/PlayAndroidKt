package jetpack.room.page

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ActivityPagingBinding
import jetpack.room.AppDataBase
import jetpack.room.UserDao
import jetpack.room.page.sp.UserSPAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class PagingActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PagingActivity"
    }

    private lateinit var dataBinding: ActivityPagingBinding

    private val userAdapter by lazy { UserAdapter() }
    private val userSPAdapter by lazy { UserSPAdapter() }
    private val viewModel = PagingViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging)
        dataBinding.lifecycleOwner = this

        dataBinding.recyclerview.layoutManager = LinearLayoutManager(this)
//        dataBinding.recyclerview.adapter = userAdapter
        dataBinding.recyclerview.adapter = userSPAdapter

        dataBinding.btnAdd.setOnClickListener {
            lifecycleScope.launch {
//                viewModel.usersFlow.collectLatest { pagingData ->
                viewModel.usersWithSPFlow.collectLatest { pagingData ->
                    Log.d(TAG, "数据返回")
//                    userAdapter.submitData(pagingData)
                    userSPAdapter.submitData(pagingData)
                }
            }
        }

        dataBinding.btnModify.setOnClickListener {
//            viewModel.modify()
            userAdapter.retry()
        }

        lifecycleScope.launch {
            userAdapter.loadStateFlow.collectLatest { loadStates ->
//            userSPAdapter.loadStateFlow.collectLatest { loadStates ->
                when (val state = loadStates.refresh) {
                    is LoadState.Loading -> {
                        //表示列表正在加载…
                        Log.d(TAG, "加载中...")
                    }
                    is LoadState.Error -> {
                        //表示加载失败, 里面存有异常信息
                        Log.d(TAG, "加载出错...${state.error}")
                    }
                    is LoadState.NotLoading -> {
                        //表示 加载完毕, 并且界面也已相应更新
                        Log.d(TAG, "加载完成 === > ")
                    }
                }
            }
        }

    }

}