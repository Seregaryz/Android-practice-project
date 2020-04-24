package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignInViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_sign_in, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToFragment(ProfileFragment.newInstance())
        }
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    private fun initClickListeners() {
        btn_auth.setOnClickListener { v -> signIn() }
        btn_google_sign_in.setOnClickListener { v -> signInWithGoogle() }

    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(SignInViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    fun signIn() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        //authenticate user
        activity?.parent?.let {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(it) { task ->
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
//                    progressBar.visibility = View.GONE
                    if (!task.isSuccessful) {
                        // there was an error
//                        if (password.length < 6) {
//                            ti_password.error = getString(R.string.error_pass_length)
//                        } else {
//                            Snackbar.make(container, R.string.error_signin, Snackbar.LENGTH_SHORT).show()
//                        }
                        //Snackbar.make(findViewById(android.R.id.content), "Failed", Snackbar.LENGTH_LONG)
                        //   .show()
                    } else {
                        navigateToFragment(ProfileFragment.newInstance())
                    }
                }
        }
    }

    private fun signInWithGoogle() {

    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun getCurrentUser() {
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

    private fun navigateToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
