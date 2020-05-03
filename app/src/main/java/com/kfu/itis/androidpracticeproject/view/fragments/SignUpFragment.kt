package com.kfu.itis.androidpracticeproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.signUpComponent.inject(this)
        initViewModel()
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    private fun createAccount(email: String, password: String) {
        viewModel.createAccount(email, password)
    }

    private fun initClickListeners() {
        btn_sign_up.setOnClickListener { v -> }
        reg_btn_sign_in.setOnClickListener { v -> }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
    }


}
