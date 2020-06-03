package com.kfu.itis.androidpracticeproject.view_model

import android.content.ContentValues
import android.util.Log
import com.kfu.itis.domain.interactor.UserInteractor
import com.kfu.itis.domain.model.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private var userInteractor: UserInteractor
) : BaseViewModel() {

    fun createAccount(email: String, password: String, username: String): Boolean {
        val userId = userInteractor.createAccount(email, password)
        var isSuccess = true
        if (userId != "not found") {
            val currentUser = User(userId, username, email, 0, 0, 0, false)
            disposables.add(
                userInteractor.saveUserInLocalBd(currentUser)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete { Log.e(ContentValues.TAG, "created in Bd") }
                    .subscribe({
                        Log.e(ContentValues.TAG, "created in Bd")
                    }, {
                        isSuccess = false
                        it.printStackTrace()
                    })
            )
        } else isSuccess = false
        return isSuccess
    }
}
