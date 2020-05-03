package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.DisputeCreatingModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.DisputeCreatingFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DisputeCreatingModule::class])
interface DisputeCreatingComponent {

    fun inject(disputeCreatingFragment: DisputeCreatingFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): DisputeCreatingComponent

    }
}