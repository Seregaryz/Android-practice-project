package com.kfu.itis.androidpracticeproject

import android.app.Application
import com.kfu.itis.androidpracticeproject.di.component.AppComponent
import com.kfu.itis.androidpracticeproject.di.component.PresentationComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var presentationComponent: PresentationComponent
    }
    override fun onCreate() {
        super.onCreate()
        presentationComponent = appComponent.plusPresentationComponent()
            .viewModelModule()
            .build()

    }

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }

}