package com.kpfu.itis.data.di

import com.kpfu.itis.data.di.scope.DataScope
import dagger.Subcomponent
import retrofit2.Retrofit

@DataScope
@Subcomponent(modules = [NetworkModule::class, RepositoryModule::class])
interface DataComponent {

    fun provideRetrofit(): Retrofit

//    fun provideDisputeRepository(): DisputeRepository
//
//    fun provideUserRepository(): UserRepository

    @Subcomponent.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): DataComponent
    }
}