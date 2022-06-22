package jetpack.room.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.playandroid_kt.R
import com.example.playandroid_kt.databinding.ItemUserBinding
import jetpack.room.User

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
class UserAdapter : PagingDataAdapter<User, UserViewHolder>(diff) {

    companion object {

        val diff = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.idNo == newItem.idNo
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val dataBinding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.dataBinding.user = getItem(position)
    }

}