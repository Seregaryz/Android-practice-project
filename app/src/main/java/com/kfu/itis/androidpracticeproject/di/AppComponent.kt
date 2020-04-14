package com.kfu.itis.androidpracticeproject.di

import android.content.Context
import com.kpfu.itis.core_db.AppDatabase
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, com.kpfu.itis.data.di.NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun getContext(): Context

    fun provideDatabase(): AppDatabase
}