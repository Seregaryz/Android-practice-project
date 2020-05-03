package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.ShopViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ShopModule {

    @IntoMap
    @Binds
    @ViewModelKey(ShopViewModel::class)
    fun bindShopViewModel(shopViewModel: ShopViewModel): ViewModel

}
