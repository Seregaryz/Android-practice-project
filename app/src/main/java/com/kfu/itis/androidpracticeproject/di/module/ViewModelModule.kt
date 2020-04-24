package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kfu.itis.androidpracticeproject.view_model.MyViewModelFactory
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import com.kfu.itis.androidpracticeproject.view_model.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(SignInViewModel::class)
    fun bindComicsViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

}
