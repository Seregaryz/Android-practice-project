package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.ViewModelModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ViewModelModule::class])
interface PresentationComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): PresentationComponent
    }

}