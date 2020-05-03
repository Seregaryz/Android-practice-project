package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignUpModule {

    @IntoMap
    @Binds
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

}
