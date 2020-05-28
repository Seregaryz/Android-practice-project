package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.DisputeModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.DisputeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DisputeModule::class])
interface DisputeComponent {

    fun inject(disputeFragment: DisputeFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): DisputeComponent

    }
}