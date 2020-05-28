package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
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
            if (createAccount(email, password)) {
                view?.let { it1 -> Snackbar.make(it1, "Success", Snackbar.LENGTH_SHORT).show() }
            } else view?.let { it1 -> Snackbar.make(it1, "Error", Snackbar.LENGTH_SHORT).show() }
        }
        reg_btn_sign_in.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signInFragment)
        }
    }

    private fun createAccount(email: String, password: String): Boolean {
        return viewModel.createAccount(email, password)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

    companion object {
        fun newInstance(): SignUpFragment = SignUpFragment()
    }

}
