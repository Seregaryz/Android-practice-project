package com.kfu.itis.domain.interactor


import com.kfu.itis.domain.model.user.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserInteractor {
    fun getUser(id: String): Observable<User>

    fun getUsers(usersId: List<String>): List<User>

    fun updateUserInLocalBd(user: User): Completable

    fun currentUserIsNull(): Boolean

    fun signIn(email: String, password: String): String

    fun signInWithGoogle(): Boolean

    fun getCurrentUser(): User

    fun getCurrentUserId(): String

    fun createAccount(email: String, password: String): String

    fun saveUserInLocalBd(user: User): Completable

    fun signOut()

    fun signOutInLocalBd(user: User): Completable

    fun signInLocalBd(user: User): Completable
}
