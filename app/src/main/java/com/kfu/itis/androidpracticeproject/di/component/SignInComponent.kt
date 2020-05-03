package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.SignInModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.SignInFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SignInModule::class])
interface SignInComponent {

    fun inject(signInFragment: SignInFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignInComponent

    }
}
