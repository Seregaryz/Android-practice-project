package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.DisputeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DisputeListModule {

    @IntoMap
    @Binds
    @ViewModelKey(DisputeListViewModel::class)
    fun bindDisputeListViewModel(disputeListViewModel: DisputeListViewModel): ViewModel

}
