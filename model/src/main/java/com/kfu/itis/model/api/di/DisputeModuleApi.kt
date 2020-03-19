package com.kfu.itis.model.api.di

import com.kfu.itis.model.api.domain.interfaces.DisputeInteractor
import com.kfu.itis.model.api.domain.interfaces.DisputeRepository

interface DisputeModuleApi {

    fun provideDisputeRepository(): DisputeRepository

    fun provideDisputeInteractor(): DisputeInteractor
}