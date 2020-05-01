package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_sign_in, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCurrentUser()
        initClickListeners()
//        auth = FirebaseAuth.getInstance()
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    private fun initClickListeners() {
        btn_auth.setOnClickListener {
            run {
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                if (viewModel.signIn(email, password)) {
                    navigateToFragment(ProfileFragment.newInstance())
                }
            }
        }
        btn_google_sign_in.setOnClickListener {
            run {
                viewModel.signInWithGoogle()
            }
        }
        auth_btn_sign_up.setOnClickListener {
            navigateToFragment(SignUpFragment.newInstance())
        }
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

    private fun checkCurrentUser() {
        if (!viewModel.currentUserIsNull()) {
            navigateToFragment(ProfileFragment.newInstance())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
