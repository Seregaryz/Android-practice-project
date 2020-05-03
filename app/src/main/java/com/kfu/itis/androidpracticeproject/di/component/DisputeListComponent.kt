package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.DisputeListModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.DisputesFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DisputeListModule::class])
interface DisputeListComponent {

    fun inject(disputesFragment: DisputesFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): DisputeListComponent

    }

}