package com.kpfu.itis.data.repository

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kfu.itis.domain.R
import com.kfu.itis.domain.model.user.User
import com.kfu.itis.domain.reposirory.UserRepository
import com.kpfu.itis.core_db.dao.UserDAO
import com.kpfu.itis.data.mappers.UserMapper
import javax.inject.Inject

public class UserRepositoryImpl @Inject constructor(
    private var auth: FirebaseAuth,
    private var userDAO: UserDAO,
    private var context: Context
) : UserRepository {

    override fun getUser(id: Int): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsers(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currentUserIsNull(): Boolean {
        val currentUser = auth.currentUser
        return currentUser == null
    }

    override fun signIn(email: String, password: String): Boolean {
        //authenticate user
        var res = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    res = false
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        return res
    }

    override fun signInWithGoogle(): Boolean {
        var res = true
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.default_web_client_id.toString())
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
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
                    if (localUser != null) {
                        userDAO.save(localUser)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    //Snackbar.make(binding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    res = false
                }
            }
        return res
    }

    override fun getCurrentUser() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

    companion object {
        private const val TAG = "Authorization"
    }
}