package jetpack.room.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import jetpack.room.AppDataBase
import jetpack.room.page.sp.UIModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class PagingViewModel : ViewModel() {

    private val userDao = AppDataBase.getDataBase().getUserDao()
    private val userSource = UserSource(userDao)

    private val pageConfig = PagingConfig(
        pageSize = 3,//每页多少个条目；必填
//        prefetchDistance = 5,//预加载下一页的距离，滑动到倒数第几个条目就加载下一页，无缝加载（可选）默认值是pageSize
//        enablePlaceholders = false,//是否启用条目占位，当条目总数量确定的时候；列表一次性展示所有条目，但是没有数据；在adapter的onBindViewHolder里面绑定数据时候，是空数据，判断是空数据展示对应的占位item；可选，默认开启。
        initialLoadSize = 3,//第一页加载条目数量 ，可选，默认值是 3*pageSize （有时候需要第一页多点数据可用）
//        maxSize = 50,//定义列表最大数量；可选，默认值是：Int.MAX_VALUE
    )

    val usersFlow = Pager(pageConfig) {
        userSource
    }.flow.map { pagingData ->
        //这里可以转换数据
//        pagingData.map {
//            it.copy(name = "ccccchhhhh")
//        }
        //这里可以过滤数据
//        pagingData.filter { it.idNo.length <= 5 }
        pagingData
    }.cachedIn(viewModelScope)

    val usersWithSPFlow = Pager(pageConfig) {
        userSource
    }.flow.map { pagingData ->
        pagingData.map {
            UIModel.UserModel(it)
        }.insertSeparators { before, after ->
            when {
                //第一行加一个分隔符
                before == null -> UIModel.SPModel("HEADER")
                //最后一行加一个分隔符
                after == null -> UIModel.SPModel("FOOTER")
                //前后证件号长度不一致
                before.user.idNo.length != after.user.idNo.length -> UIModel.SPModel("证件号长度不一致")
                //其余情况不加分隔符
                else -> null
            }
        }
    }.cachedIn(viewModelScope)

    fun modify() {
        viewModelScope.launch {
            val user = userDao.getAllUsers()[1]
            userDao.update(user.copy(name = user.name + "6666"))
        }
    }

}