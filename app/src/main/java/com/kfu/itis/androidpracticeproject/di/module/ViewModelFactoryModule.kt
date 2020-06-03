package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModelProvider
import com.kfu.itis.androidpracticeproject.di.vm_provide.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}
