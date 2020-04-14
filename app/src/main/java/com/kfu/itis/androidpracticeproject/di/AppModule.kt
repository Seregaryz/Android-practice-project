package com.kfu.itis.androidpracticeproject.di

import android.content.Context
import com.kfu.itis.androidpracticeproject.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun bindContext(application: App): Context = application.applicationContext

}
