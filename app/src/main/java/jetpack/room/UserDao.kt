package jetpack.room

import androidx.paging.DataSource
import androidx.room.*

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User LIMIT :limit offset :offset")
    suspend fun getUsersLimit(offset: Int, limit: Int): List<User>

    @Query("SELECT COUNT(*) From User")
    suspend fun count(): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg users: User)

    @Query("SELECT * FROM User WHERE idNo = :idNo")
    suspend fun findUser(idNo: String): User?

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM User")
    suspend fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg users: User)

}