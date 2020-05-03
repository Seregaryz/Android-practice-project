package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.SignUpModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.SignUpFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SignUpModule::class])
interface SignUpComponent {

    fun inject(signUpFragment: SignUpFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignUpComponent

    }
}
