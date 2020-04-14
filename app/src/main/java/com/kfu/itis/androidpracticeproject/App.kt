package com.kfu.itis.androidpracticeproject

import android.app.Application
import com.kfu.itis.androidpracticeproject.di.AppComponent
import com.kfu.itis.androidpracticeproject.di.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()

    }
}