package com.kfu.itis.androidpracticeproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R

class ProfileFragment : Fragment() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    private var viewModel: ProfileViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.profileComponent.inject(this)
        //initViewModel()
    }

//    private fun initViewModel() {
//        val resultViewModel by lazy {
//            ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
//        }
//        this.viewModel = resultViewModel
//    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}
