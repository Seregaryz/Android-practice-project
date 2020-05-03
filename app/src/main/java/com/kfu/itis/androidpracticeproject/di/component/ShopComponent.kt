package com.kfu.itis.androidpracticeproject.di.component

import com.kfu.itis.androidpracticeproject.di.module.ShopModule
import com.kfu.itis.androidpracticeproject.di.scope.FragmentScope
import com.kfu.itis.androidpracticeproject.view.fragments.ShopFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ShopModule::class])
interface ShopComponent {

    fun inject(ShopFragment: ShopFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ShopComponent

    }
}