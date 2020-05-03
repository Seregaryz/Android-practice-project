package com.kfu.itis.androidpracticeproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.LikedDisputesViewModel
import javax.inject.Inject

class LikedDisputesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: LikedDisputesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_liked_disputes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.likedDisputesComponent.inject(this)
        initViewModel()
    }

    private fun initViewModel() {
        val resultViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(LikedDisputesViewModel::class.java)
        }
        this.viewModel = resultViewModel
    }
}
