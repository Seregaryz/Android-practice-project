package com.kfu.itis.androidpracticeproject

import android.app.Application
import com.kfu.itis.androidpracticeproject.di.component.*
import com.kfu.itis.androidpracticeproject.di.module.AppModule
import com.kfu.itis.domain.di.DomainModule
import com.kpfu.itis.core_db.di.DatabaseModule
import com.kpfu.itis.data.di.NetworkModule
import com.kpfu.itis.data.di.RepositoryModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var disputeListComponent: DisputeListComponent
        lateinit var disputeCreatingComponent: DisputeCreatingComponent
        lateinit var likedDisputesComponent: LikedDisputesComponent
        lateinit var shopComponent: ShopComponent
        lateinit var profileComponent: ProfileComponent
        lateinit var signUpComponent: SignUpComponent
        lateinit var signInComponent: SignInComponent
    }
    override fun onCreate() {
        super.onCreate()
        init(this)
        disputeListComponent = appComponent.plusDisputeListComponent()
            .build()
        disputeCreatingComponent = appComponent.plusDisputeCreatingComponent()
            .build()
        likedDisputesComponent = appComponent.plusLikedDisputesComponent()
            .build()
        shopComponent = appComponent.plusShopComponent()
            .build()
        profileComponent = appComponent.plusProfileComponent()
            .build()
        signInComponent = appComponent.plusSignInComponent()
            .build()
        signUpComponent = appComponent.plusSignUpComponent()
            .build()
    }

    private fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .domainModule(DomainModule())
            .databaseModule(DatabaseModule())
            .appModule(AppModule())
            .build()
    }

}
