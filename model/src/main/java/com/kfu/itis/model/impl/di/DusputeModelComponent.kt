package com.kfu.itis.model.impl.di

import dagger.Component

@Component(
    dependencies = [
        DisputeModelDependencies::class
    ],
    modules = [
        DisputeModelModule::class
    ]
)
interface DusputeModelComponent {

}