package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.user.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun getUser(id: String): Observable<User>

    fun saveUserInLocalBd(user: User): Completable

    fun updateUserInLocalBd(user: User): Completable

    fun updateUser(user: User): User

    fun currentUserIsNull(): Boolean

    fun signIn(email: String, password: String): String

    fun signInWithGoogle(): Boolean

    fun getCurrentUser(): User

    fun getCurrentUserId(): String

    fun createAccount(email: String, password: String): String

    fun signOut()

    fun signOutInLocalBd(user: User): Completable

    fun signInLocalBd(user: User): Completable
}