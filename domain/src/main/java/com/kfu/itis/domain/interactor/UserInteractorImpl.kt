package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.user.User
import com.kfu.itis.domain.reposirory.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserInteractor {

    override fun getUser(id: String): Observable<User> {
        return userRepository.getUser(id)
    }

    override fun getUsers(usersId: List<String>): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserInLocalBd(user: User): Completable {
        return userRepository.updateUserInLocalBd(user)
    }

    override fun currentUserIsNull(): Boolean {
        return userRepository.currentUserIsNull()
    }

    override fun signIn(email: String, password: String): String {
        return userRepository.signIn(email, password)
    }

    override fun signInWithGoogle(): Boolean {
        return userRepository.signInWithGoogle()
    }

    override fun getCurrentUser(): User {
        return userRepository.getCurrentUser()
    }

    override fun getCurrentUserId(): String {
        return userRepository.getCurrentUserId()
    }

    override fun createAccount(email: String, password: String): String {
        return userRepository.createAccount(email, password)
    }

    override fun saveUserInLocalBd(user: User): Completable {
        return userRepository.saveUserInLocalBd(user)
    }

    override fun signOut() {
        userRepository.signOut()
    }

    override fun signOutInLocalBd(user: User): Completable {
        return userRepository.signOutInLocalBd(user)
    }

    override fun signInLocalBd(user: User): Completable {
        return userRepository.signInLocalBd(user)
    }

}