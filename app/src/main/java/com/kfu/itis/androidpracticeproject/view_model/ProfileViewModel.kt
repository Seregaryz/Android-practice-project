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

class ProfileViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : BaseViewModel() {

    private val userMutableLiveData = MutableLiveData<User>()
    var userLiveData: LiveData<User> = userMutableLiveData
    lateinit var currentUser: User

    fun getUser(userId: String) {
        disposables.add(
            userInteractor.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(ContentValues.TAG, userMutableLiveData.value?.email)
                    userMutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun updateUserInLocalBd(user: User) {
        disposables.add(
            userInteractor.updateUserInLocalBd(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )

    }

    fun getCurrentUserId(): String {
        return userInteractor.getCurrentUserId()
    }

    fun signOut() {
        val currentUserId = userInteractor.getCurrentUserId()
        userInteractor.signOut()
        disposables.add(
            userInteractor.getUser(currentUserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disposables.add(
                        userInteractor.signOutInLocalBd(currentUser)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    )
                }, {
                    it.printStackTrace()
                })
        )

    }
}