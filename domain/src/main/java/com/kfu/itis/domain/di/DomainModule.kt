package com.kfu.itis.domain.di

import com.kfu.itis.domain.interactor.DisputeInteractor
import com.kfu.itis.domain.interactor.UserInteractor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideDisputeInteractor(disputeInteractor: DisputeInteractor): DisputeInteractor =
        disputeInteractor

    @Provides
    fun provideUserInteractor(userInteractor: UserInteractor): UserInteractor =
        userInteractor
}