package com.kfu.itis.domain.di

import com.kfu.itis.domain.interactor.DisputeInteractor
import com.kfu.itis.domain.interactor.DisputeInteractorImpl
import com.kfu.itis.domain.interactor.UserInteractor
import com.kfu.itis.domain.interactor.UserInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideDisputeInteractor(disputeInteractor: DisputeInteractorImpl): DisputeInteractor =
        disputeInteractor

    @Provides
    @Singleton
    fun provideUserInteractor(userInteractor: UserInteractorImpl): UserInteractor =
        userInteractor
}
