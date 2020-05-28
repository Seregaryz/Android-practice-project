package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.DisputeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DisputeModule {

    @IntoMap
    @Binds
    @ViewModelKey(DisputeViewModel::class)
    fun bindDisputeViewModel(disputeViewModel: DisputeViewModel): ViewModel
}