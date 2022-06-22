package jetpack.room.page.sp

import jetpack.room.User

/**
 * @description
 * @author ch
 * @date 2022/4/12
 * @edit
 */
sealed class UIModel {

    data class UserModel(val user: User) : UIModel()

    data class SPModel(val description: String) : UIModel()

}