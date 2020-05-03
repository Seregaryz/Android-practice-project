package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.user.User

interface UserInteractor {
    fun getUser(id: Int): User

    fun getUsers(usersId: IntArray): List<User>

    fun currentUserIsNull(): Boolean

    fun signIn(email: String, password: String): Boolean

    fun signInWithGoogle(): Boolean

    fun getCurrentUser()

    fun createAccount(email: String, password: String): Boolean
}