package com.kpfu.itis.data.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kfu.itis.domain.R
import com.kfu.itis.domain.reposirory.DisputeRepository
import com.kfu.itis.domain.reposirory.UserRepository
import com.kpfu.itis.data.repository.DisputeRepositoryImpl
import com.kpfu.itis.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDisputeRepository(disputeRepository: DisputeRepositoryImpl):
            DisputeRepository = disputeRepository

    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepositoryImpl):
            UserRepository = userRepository

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideGoogleSignInOptions(): GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.default_web_client_id.toString())
            .requestEmail()
            .build()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(context: Context, gso: GoogleSignInOptions): GoogleSignInClient =
        GoogleSignIn.getClient(context, gso)

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

}