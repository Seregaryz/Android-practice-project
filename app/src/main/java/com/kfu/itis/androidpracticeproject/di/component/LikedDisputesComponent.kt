package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.LikedDisputesModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.LikedDisputesFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [LikedDisputesModule::class])
interface LikedDisputesComponent {

    fun inject(likedDisputesFragment: LikedDisputesFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): LikedDisputesComponent

    }
}
