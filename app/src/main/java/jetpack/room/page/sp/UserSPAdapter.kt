package jetpack.room.page.sp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.playandroid_kt.R
import jetpack.room.page.UserViewHolder

/**
 * @description
 * @author ch
 * @date 2022/4/12
 * @edit
 */
class UserSPAdapter : PagingDataAdapter<UIModel, RecyclerView.ViewHolder>(DIFF) {

    object DIFF : DiffUtil.ItemCallback<UIModel>() {

        override fun areItemsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
            val isSameUserItem = oldItem is UIModel.UserModel
                    && newItem is UIModel.UserModel
                    && oldItem.user.idNo == newItem.user.idNo
            val isSameSPItem = oldItem is UIModel.SPModel
                    && newItem is UIModel.SPModel
                    && oldItem.description == newItem.description
            return isSameUserItem || isSameSPItem
        }

        override fun areContentsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        Log.d("chenhui", "count:$count")
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return when (peek(position)) {
            is UIModel.UserModel -> {
                R.layout.item_user
            }
            else -> {
                R.layout.item_sp
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        if (model is UIModel.UserModel) {
            (holder as UserViewHolder).dataBinding.user = model.user
        } else {
            (holder as SPViewHolder).dataBinding.spModel = (model as UIModel.SPModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_user -> {
                UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
            }
            else -> {
                SPViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
            }
        }
    }


}