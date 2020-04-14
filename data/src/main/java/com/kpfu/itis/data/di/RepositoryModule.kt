package com.kpfu.itis.data.di

import com.kpfu.itis.data.repository.DisputeRepository
import com.kpfu.itis.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDisputeRepository(disputeRepository: DisputeRepository): DisputeRepository =
        disputeRepository

    @Provides
    fun provideUserRepository(userRepository: UserRepository): UserRepository =
        userRepository
}