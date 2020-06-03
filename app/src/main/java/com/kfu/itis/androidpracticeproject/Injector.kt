package com.kfu.itis.androidpracticeproject

import com.kfu.itis.androidpracticeproject.di.component.*
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kfu.itis.domain.di.DomainModule
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.NetworkModule
import com.kpfu.itis.data.di.RepositoryModule

object Injector {

    lateinit var appComponent: AppComponent
    var disputeListComponent: DisputeListComponent? = null
    var disputeCreatingComponent: DisputeCreatingComponent? = null
    var likedDisputesComponent: LikedDisputesComponent? = null
    var shopComponent: ShopComponent? = null
    var profileComponent: ProfileComponent? = null
    var signUpComponent: SignUpComponent? = null
    var signInComponent: SignInComponent? = null
    var disputeComponent: DisputeComponent? = null

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .domainModule(DomainModule())
            .databaseModule(DatabaseModule())
            .appModule(AppModule())
            .build()
    }

    fun plusDisputeListComponent(): DisputeListComponent = disputeListComponent
        ?: appComponent
            .plusDisputeListComponent()
            .build().also {
                disputeListComponent = it
            }

    fun clearDisputeListComponent() {
        disputeListComponent = null
    }

    fun plusDisputeCreatingComponent(): DisputeCreatingComponent = disputeCreatingComponent
        ?: appComponent
            .plusDisputeCreatingComponent()
            .build().also {
                disputeCreatingComponent = it
            }

    fun clearDisputeCreatingComponent() {
        disputeCreatingComponent = null
    }

    fun plusLikedDisputesComponent(): LikedDisputesComponent = likedDisputesComponent
        ?: appComponent
            .plusLikedDisputesComponent()
            .build().also {
                likedDisputesComponent = it
            }

    fun clearLikedDisputesComponent() {
        likedDisputesComponent = null
    }

    fun plusShopComponent(): ShopComponent = shopComponent
        ?: appComponent
            .plusShopComponent()
            .build().also {
                shopComponent = it
            }

    fun clearShopComponent() {
        shopComponent = null
    }

    fun plusProfileComponent(): ProfileComponent = profileComponent
        ?: appComponent
            .plusProfileComponent()
            .build().also {
                profileComponent = it
            }

    fun clearProfileComponent() {
        profileComponent = null
    }

    fun plusSignInComponent(): SignInComponent = signInComponent
        ?: appComponent
            .plusSignInComponent()
            .build().also {
                signInComponent = it
            }

    fun clearSignInComponent() {
        signInComponent = null
    }

    fun plusSignUpComponent(): SignUpComponent = signUpComponent
        ?: appComponent
            .plusSignUpComponent()
            .build().also {
                signUpComponent = it
            }

    fun clearSignUpComponent() {
        signUpComponent = null
    }

    fun plusDisputeComponent(): DisputeComponent = disputeComponent
        ?: appComponent
            .plusDisputeComponent()
            .build().also {
                disputeComponent = it
            }

    fun clearDisputeComponent() {
        disputeComponent = null
    }

}
