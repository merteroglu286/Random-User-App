package tr.main.randomuserapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tr.main.randomuserapp.model.UserEntity
import tr.main.randomuserapp.service.UserHistoryDao


@Database(entities = [UserEntity::class],version = 1)
abstract class UserHistoryDatabase:RoomDatabase() {

    abstract fun userHistoryDao():UserHistoryDao

    companion object {

        @Volatile
        private var INSTANCE: UserHistoryDatabase? = null

        fun getInstance(context: Context): UserHistoryDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserHistoryDatabase::class.java,
                        "user_history_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}