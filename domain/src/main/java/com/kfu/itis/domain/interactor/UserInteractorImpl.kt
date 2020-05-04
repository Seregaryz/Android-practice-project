package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.user.User
import com.kfu.itis.domain.reposirory.UserRepository
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserInteractor {

    override fun getUser(id: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsers(usersId: List<String>): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentUserIsNull(): Boolean {
        return userRepository.currentUserIsNull()
    }

    override fun signIn(email: String, password: String): Boolean {
        return userRepository.signIn(email, password)
    }

    override fun signInWithGoogle(): Boolean {
        return userRepository.signInWithGoogle()
    }

    override fun getCurrentUser() {
    }

    override fun createAccount(email: String, password: String): Boolean {
        return userRepository.createAccount(email, password)
    }

}