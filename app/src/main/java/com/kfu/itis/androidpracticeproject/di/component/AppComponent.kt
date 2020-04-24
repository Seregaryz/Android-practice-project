package com.kfu.itis.androidpracticeproject.di.component

import android.content.Context
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kpfu.itis.core_db.AppDatabase
import com.kpfu.itis.core_db.di.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {

    fun getContext(): Context

    fun provideDatabase(): AppDatabase

    fun plusPresentationComponent(): PresentationComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}