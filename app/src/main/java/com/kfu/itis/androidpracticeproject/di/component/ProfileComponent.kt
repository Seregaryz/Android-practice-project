package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.ProfileModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.ProfileFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    fun inject(ProfileFragment: ProfileFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ProfileComponent

    }
}
