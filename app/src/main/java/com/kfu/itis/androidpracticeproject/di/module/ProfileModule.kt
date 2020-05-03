package com.kfu.itis.androidpracticeproject.di.module

import androidx.lifecycle.ViewModel
import com.kfu.itis.androidpracticeproject.di.vm_provide.ViewModelKey
import com.kfu.itis.androidpracticeproject.view_model.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileModule {

    @IntoMap
    @Binds
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

}
