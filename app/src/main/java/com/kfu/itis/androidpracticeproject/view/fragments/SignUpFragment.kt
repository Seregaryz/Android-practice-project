package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusSignUpComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_sign_up.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val username = et_username.text.toString()
            val repeatedPassword = et_repeat_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty() && repeatedPassword.isNotEmpty()) {
                if (password == repeatedPassword) {
                    if (createAccount(email, password, username)) {
                        tv_errors.isVisible = false
                        Navigation.findNavController(it).navigate(R.id.signInFragment)
                    } else {
                        tv_errors.text = CONNECTION_ERROR
                        tv_errors.isVisible = true
                    }
                } else {
                    tv_errors.text = PASSWORD_MISSMATCH_ERROR
                    tv_errors.isVisible = true
                }
            } else {
                tv_errors.text = EMPTY_FIELDS_ERROR
                tv_errors.isVisible = true
            }
        }
        reg_btn_sign_in.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signInFragment)
        }
    }

    private fun createAccount(email: String, password: String, username: String): Boolean {
        return viewModel.createAccount(email, password, username)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

    companion object {
        const val PASSWORD_MISSMATCH_ERROR = "Password mismatch"
        const val EMPTY_FIELDS_ERROR = "You should edit all fields"
        const val CONNECTION_ERROR = "Error of connection"
        fun newInstance(): SignUpFragment = SignUpFragment()
    }

}
