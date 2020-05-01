package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.ViewModel
import com.kfu.itis.domain.interactor.UserInteractor
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {


    fun signIn(email: String, password: String): Boolean {
        return userInteractor.signIn(email, password)
    }

    fun signInWithGoogle(): Boolean {
        return userInteractor.signInWithGoogle()
    }

    fun currentUserIsNull(): Boolean {
        return userInteractor.currentUserIsNull()
    }

}