package com.kfu.itis.androidpracticeproject

import android.app.Application
import com.kfu.itis.androidpracticeproject.di.component.AppComponent
import com.kfu.itis.androidpracticeproject.di.component.DaggerAppComponent
import com.kfu.itis.androidpracticeproject.di.component.PresentationComponent
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.DataComponent
import com.kpfu.itis.data.di.NetworkModule
import com.kpfu.itis.data.di.RepositoryModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var presentationComponent: PresentationComponent
        lateinit var dataComponent: DataComponent
    }
    override fun onCreate() {
        super.onCreate()
        init(this)
        presentationComponent = appComponent.plusPresentationComponent()
            .build()
        dataComponent = appComponent.plusDataComponent()
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .databaseModule(DatabaseModule())
            .appModule(AppModule())
            .build()
    }

}