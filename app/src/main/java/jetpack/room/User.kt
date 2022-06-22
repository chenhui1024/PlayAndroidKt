package jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
@Entity
data class User(
    @PrimaryKey val idNo: String,
    val name: String,
    val age: Int,
    val address: String
)