package jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @description
 * @author ch
 * @date 2022/4/11
 * @edit
 */
@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "db_play_android"
        private var INSTANCE: AppDataBase? = null

        @Synchronized
        fun init(appContext: Context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    appContext,
                    AppDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()
            }
        }

        fun getDataBase(): AppDataBase {
            return INSTANCE!!
        }
    }

    abstract fun getUserDao(): UserDao

}