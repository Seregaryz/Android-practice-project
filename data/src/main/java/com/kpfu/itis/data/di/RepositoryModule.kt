package com.kpfu.itis.data.di

import com.google.firebase.auth.FirebaseAuth
import com.kpfu.itis.data.di.scope.DataScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @DataScope
    fun provideDisputeRepository(disputeRepository: com.kfu.itis.domain.reposirory.DisputeRepository): com.kfu.itis.domain.reposirory.DisputeRepository =
        disputeRepository

    @Provides
    @DataScope
    fun provideUserRepository(userRepository: com.kfu.itis.domain.reposirory.UserRepository): com.kfu.itis.domain.reposirory.UserRepository =
        userRepository

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}