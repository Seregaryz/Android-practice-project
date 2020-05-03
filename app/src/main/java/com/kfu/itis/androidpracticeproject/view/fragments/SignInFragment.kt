package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_sign_in, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.signInComponent.inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCurrentUser()
        initClickListeners()
    }

    private fun initViewModel() {
        val resultViewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(SignInViewModel::class.java)
        }
        this.viewModel = resultViewModel
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
