package com.kpfu.itis.core_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.core_db.dao.UserDAO
import com.kpfu.itis.data.model.DisputeLocal
import com.kpfu.itis.data.model.UserLocal

@Database(
    version = 1,
    entities = [
        UserLocal::class, DisputeLocal::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "app.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun userDao(): UserDAO

    abstract fun disputeDao(): DisputeDAO
}