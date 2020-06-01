package com.kfu.itis.androidpracticeproject.view_model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfu.itis.domain.interactor.UserInteractor
import com.kfu.itis.domain.model.user.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    private val userMutableLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = userMutableLiveData
    lateinit var currentUser: User


    fun signIn(email: String, password: String): Boolean {
        val userId = userInteractor.signIn(email, password)
        val isSuccess = userId != "not found"
        if (isSuccess) {
            disposables.add(
                userInteractor.getUser(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.e(ContentValues.TAG, "Get user: SignIn " + it.email)
                        userMutableLiveData.value = it
                        currentUser = it
                    }, {
                        it.printStackTrace()
                    })
            )
        }
        return isSuccess
    }

    fun signInLocalBd(user: User) {
        disposables.add(
            userInteractor.signInLocalBd(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { Log.e(ContentValues.TAG, "updated in Bd") }
                .subscribe({
                    Log.e(ContentValues.TAG, "updated in Bd")
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun signInWithGoogle(): Boolean {
        return userInteractor.signInWithGoogle()
    }

    fun currentUserIsNull(): Boolean {
        return userInteractor.currentUserIsNull()
    }

}