package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.ViewModelModule
import com.kfu.itis.androidpracticeproject.view_model.MyViewModelFactory
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import dagger.Subcomponent

@com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
@Subcomponent(
    modules = [
        ViewModelModule::class
    ]
)
interface PresentationComponent {

    fun provideViewModelFactory(): MyViewModelFactory

    fun provideSignInViewModel(): SignInViewModel

    @Subcomponent.Builder
    interface Builder {
        fun build(): PresentationComponent
    }

}