package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignInModule {

    @IntoMap
    @Binds
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(signInViewModel: SignInViewModel): ViewModel

}
