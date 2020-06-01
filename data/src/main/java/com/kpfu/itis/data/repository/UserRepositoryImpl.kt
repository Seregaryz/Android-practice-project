package com.kpfu.itis.data.repository

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.model.user.User
import com.kfu.itis.domain.reposirory.UserRepository
import com.kpfu.itis.core_db.dao.UserDAO
import com.kpfu.itis.core_db.dao.VoiceDAO
import com.kpfu.itis.core_db.model.DisputeLocal
import com.kpfu.itis.data.mappers.DisputeMapper
import com.kpfu.itis.data.mappers.UserMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var auth: FirebaseAuth,
    private var userDAO: UserDAO,
    private var voiceDao: VoiceDAO,
    private var googleSignInClient: GoogleSignInClient,
    private val database: FirebaseDatabase
) : UserRepository {

    private var myRef = database.getReference("dispute")

    override fun getUser(id: String): Observable<User> {
        return userDAO.getUser(id).map { item -> updateUser(UserMapper.toUser(item)) }
    }

    override fun updateUser(user: User): User {
        val disputes = getDisputesFromFb().blockingFirst()
        val voices = voiceDao.getVoices(user.id).blockingFirst()
        var wins = 0
        for (voice in voices) {
            for (dispute in disputes) {
                if (voice.disputeId == dispute.id && dispute.isFinished) {
                    wins++
                }
            }
        }
        val newWins = wins - user.winCount
        user.pointsCount = user.pointsCount + newWins * 10
        user.winCount = wins
        return user
    }

    private fun getDisputesFromFb(): Observable<List<Dispute>> {
        val query = myRef
        val indicator = object :
            GenericTypeIndicator<Map<String, @kotlin.jvm.JvmSuppressWildcards DisputeLocal>>() {}
        return RxFirebaseDatabase.observeSingleValueEvent(query)
            .toObservable()
            .flatMap { list ->
                Observable.fromIterable(list.getValue(indicator)?.values)
                    .map { item -> DisputeMapper.toDispute(item) }
                    .toList()
                    .toObservable()
            }
    }

    override fun updateUserInLocalBd(user: User): Completable {
        return userDAO.updateUser(UserMapper.toLocalUserFromUser(user))
    }

    override fun saveUserInLocalBd(user: User): Completable {
        return userDAO.save(UserMapper.toLocalUserFromUser(user))
    }

    override fun currentUserIsNull(): Boolean {
        val currentUser = auth.currentUser
        return currentUser == null
    }

    override fun signIn(email: String, password: String): String {
        //authenticate user
        var res = true
        var userId = ""
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success ${auth.currentUser?.uid}")
                    userId = auth.currentUser?.uid ?: "not found"
                    res = true
                } else {
                    res = false
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        return userId
    }

    override fun signInWithGoogle(): Boolean {
        var res = false
        val signInIntent = googleSignInClient.signInIntent
        val account = GoogleSignIn.getSignedInAccountFromIntent(signInIntent).result
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    val localUser = user?.let { UserMapper.toLocalUser(it) }
                    if (localUser != null && userDAO.getUser(user.uid) == null) {
                        userDAO.save(localUser)
                    }
                    res = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    //Snackbar.make(binding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                }
            }
        return res
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun signInLocalBd(user: User): Completable {
        user.isAuthorized = true
        return userDAO.updateUser(UserMapper.toLocalUserFromUser(user))
    }

    override fun signOutInLocalBd(user: User): Completable {
        user.isAuthorized = false
        return userDAO.updateUser(UserMapper.toLocalUserFromUser(user))
    }

    override fun getCurrentUser(): User {
        val user = auth.currentUser!!
        return UserMapper.toUserFromFirebaseUser(user, 0, 0, 0)
    }

    override fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: "error"
    }

    override fun createAccount(email: String, password: String): String {
        var res = true
        var userId = ""
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success " + auth.currentUser?.uid)
                    userId = auth.currentUser?.uid ?: "not found"
                    res = true
                } else {
                    // If sign in fails, display a message to the user.
                    res = false
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                }
            }
        return userId
    }

    companion object {
        private const val TAG = "Authorization"
    }
}
