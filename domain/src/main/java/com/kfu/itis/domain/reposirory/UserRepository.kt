package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.user.User

interface UserRepository {
    fun getUser(id: String): User

    fun getUsers(): List<User>

    fun saveUser(user: User)

    fun currentUserIsNull(): Boolean

    fun signIn(email: String, password: String): Boolean

    fun signInWithGoogle(): Boolean

    fun getCurrentUser(): User?

    fun getCurrentUserId(): String

    fun createAccount(email: String, password: String): Boolean
}