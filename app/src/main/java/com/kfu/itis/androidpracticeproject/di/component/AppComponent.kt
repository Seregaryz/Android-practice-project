package com.kfu.itis.androidpracticeproject.di.component

import android.content.Context
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kfu.itis.domain.di.DomainModule
import com.kpfu.itis.core_db.AppDatabase
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.DataComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, DomainModule::class])
interface AppComponent {

    fun getContext(): Context

    fun provideDatabase(): AppDatabase

    fun plusPresentationComponent(): PresentationComponent.Builder

    fun plusDataComponent(): DataComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun appModule(appModule: AppModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }
}