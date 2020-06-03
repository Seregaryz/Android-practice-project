package com.kpfu.itis.core_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.core_db.dao.UserDAO
import com.kpfu.itis.core_db.dao.VoiceDAO
import com.kpfu.itis.core_db.model.DisputeLocal
import com.kpfu.itis.core_db.model.UserLocal
import com.kpfu.itis.core_db.model.Voice

@Database(
    version = 11,
    entities = [
        UserLocal::class, DisputeLocal::class, Voice::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DATABASE_NAME = "app.db"
        private const val DISPUTE_DATABASE_NAME = "disputes.db"

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun userDao(): UserDAO

    abstract fun disputeDao(): DisputeDAO

    abstract fun voiceDao(): VoiceDAO
}
