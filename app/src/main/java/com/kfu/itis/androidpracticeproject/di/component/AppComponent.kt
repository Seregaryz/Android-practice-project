package com.kfu.itis.androidpracticeproject.di.component

import android.content.Context
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kfu.itis.androidpracticeproject.di.module.ViewModelFactoryModule
import com.kfu.itis.domain.di.DomainModule
import com.kpfu.itis.core_db.AppDatabase
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.NetworkModule
import com.kpfu.itis.data.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DatabaseModule::class, DomainModule::class,
        NetworkModule::class, RepositoryModule::class, ViewModelFactoryModule::class]
)
interface AppComponent {

    fun getContext(): Context

    fun provideDatabase(): AppDatabase

    fun provideRetrofit(): Retrofit

    fun plusSignInComponent(): SignInComponent.Builder

    fun plusSignUpComponent(): SignUpComponent.Builder

    fun plusShopComponent(): ShopComponent.Builder

    fun plusLikedDisputesComponent(): LikedDisputesComponent.Builder

    fun plusProfileComponent(): ProfileComponent.Builder

    fun plusDisputeCreatingComponent(): DisputeCreatingComponent.Builder

    fun plusDisputeListComponent(): DisputeListComponent.Builder

    fun plusDisputeComponent(): DisputeComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun domainModule(domainModule: DomainModule): Builder
        fun appModule(appModule: AppModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }
}