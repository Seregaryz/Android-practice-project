package com.kfu.itis.androidpracticeproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.signInComponent.inject(this)
        initViewModel()
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!viewModel.currentUserIsNull()) {
            Navigation.findNavController(view).navigate(R.id.profile_fragment)
        }
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    private fun initClickListeners() {
        btn_auth.setOnClickListener {
            run {
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                if (viewModel.signIn(email, password)) {
                    Navigation.findNavController(it).navigate(R.id.profile_fragment)
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

//    private fun checkCurrentUser() {
//
//    }

    companion object {
        fun newInstance(): SignInFragment = SignInFragment()
    }
}
