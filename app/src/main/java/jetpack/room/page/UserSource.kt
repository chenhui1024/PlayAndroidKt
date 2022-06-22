package jetpack.room.page

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import jetpack.room.User
import jetpack.room.UserDao

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class UserSource(
    private val userDao: UserDao
) : PagingSource<Int, User>() {

    companion object {
        private const val TAG = "UserSource"
    }

    private var isMNExit = true

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        Log.d(TAG, "getRefreshKey === >")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            Log.d(TAG, "prev:${anchorPage?.prevKey}, next:${anchorPage?.nextKey}")
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("chenhui", "key:${params.key}")
        val count = userDao.count()
        Log.d(TAG, "count:$count")
        val startPage = params.key ?: 1
        val offset = (startPage - 1) * params.loadSize
        Log.d(TAG, "偏移量:$offset")

//        if (startPage >= 2 && isMNExit) {
//            isMNExit = false
//            Log.d(TAG, "模拟异常退出=== > ")
//            return LoadResult.Error(Exception("模拟异常退出"))
//        }

        val users = userDao.getUsersLimit(offset, params.loadSize)
        val nextPage = if (offset + params.loadSize >= count) null else startPage + 1
        if (users.isEmpty().not()) {
            Log.d(TAG, "first:${users[0]}")
        }

        Log.d("chenhui", "loadSize:${params.loadSize}")
        Log.d(TAG, "user size:${users.size}")
        Log.d(TAG, "user nextPage:$nextPage")

        return LoadResult.Page(
            data = users,
            prevKey = null, // Only paging forward.  只向后加载就给 null
            nextKey = nextPage //nextKey 下一页页码;  尾页给 null;  否则当前页码加1
        )
    }
}