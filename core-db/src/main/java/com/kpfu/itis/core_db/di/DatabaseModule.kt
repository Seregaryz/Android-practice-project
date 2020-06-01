package com.kpfu.itis.core_db.di

import android.content.Context
import com.kpfu.itis.core_db.AppDatabase
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.core_db.dao.UserDAO
import com.kpfu.itis.core_db.dao.VoiceDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDAO {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideDispiteDao(appDatabase: AppDatabase): DisputeDAO {
        return appDatabase.disputeDao()
    }

    @Provides
    @Singleton
    fun provideVoicesDao(appDatabase: AppDatabase): VoiceDAO {
        return appDatabase.voiceDao()
    }
}