package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.DisputeCreatingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DisputeCreatingModule {

    @IntoMap
    @Binds
    @ViewModelKey(DisputeCreatingViewModel::class)
    fun bindDisputeCreatingViewModule(disputeCreatingViewModel: DisputeCreatingViewModel): ViewModel

}
