package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.ViewModel
import com.kfu.itis.domain.interactor.UserInteractor
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private var userInteractor: UserInteractor
) : ViewModel() {

    fun createAccount(email: String, password: String): Boolean {
        return userInteractor.createAccount(email, password)
    }
}