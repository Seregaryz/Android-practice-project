package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kfu.itis.androidpracticeproject.Injector
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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusSignInComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!viewModel.currentUserIsNull()) {
            Navigation.findNavController(view).navigate(R.id.profile_fragment)
        }
        subscribe(viewModel)
        initClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    private fun subscribe(viewModel: SignInViewModel) {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.userLiveData.value.let { it1 ->
                if (it1 != null) {
                    viewModel.signInLocalBd(it1)
                }
            }
        })
    }

    private fun initClickListeners() {
        btn_auth.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            if (viewModel.signIn(email, password)) {
                Navigation.findNavController(it).navigate(R.id.profile_fragment)
            }
        }
//        btn_google_sign_in.setOnClickListener {
//            viewModel.signInWithGoogle()
//
//        }
        auth_btn_sign_up.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpFragment)
        }
    }

//    private fun checkCurrentUser() {
//
//    }

    companion object {
        fun newInstance(): SignInFragment = SignInFragment()
    }
}
