package com.kfu.itis.model.impl.di

import com.kfu.itis.model.api.di.DisputeModuleApi
import com.kfu.itis.model.api.domain.interfaces.DisputeInteractor
import com.kfu.itis.model.api.domain.interfaces.DisputeRepository
import dagger.Module
import dagger.Provides

@Module
interface DisputeModelModule {

    @Provides
    fun provideDisputeRepository(disputeRepository: DisputeRepository): DisputeRepository =
        disputeRepository

    @Provides
    fun provideDisputeInteractor(disputeInteractor: DisputeInteractor): DisputeInteractor =
        disputeInteractor

    @Provides
    fun provideDisputeApi(disputeApi: DisputeModuleApi): DisputeModuleApi = disputeApi

}