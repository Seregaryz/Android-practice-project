package com.kfu.itis.androidpracticeproject.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ProfileViewModel
    lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = viewModel.getCurrentUserId()
        Snackbar.make(view, "$userId is email", Snackbar.LENGTH_SHORT).show()
        subscribe(viewModel, userId)
        initViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusProfileComponent().inject(this)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    private fun initViews() {
        btn_logout.setOnClickListener {
            viewModel.signOut()
            Navigation.findNavController(it).navigate(R.id.signInFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun subscribe(viewModel: ProfileViewModel, userId: String) {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.updateUserInLocalBd(it)
            viewModel.currentUser = it
            view?.let { it1 ->
                Snackbar.make(it1, it.email + " Again", Snackbar.LENGTH_SHORT).show()
            }
            val losesCount = it.voicesCount - it.winCount
            tv_name.text = it.username
            tv_first_position.text =
                tv_first_position.text.toString() + " " + it.voicesCount.toString()
            tv_wins_count.text = tv_wins_count.text.toString() + " " + it.winCount.toString()
            tv_loses_count.text = tv_loses_count.text.toString() + " " + losesCount.toString()
            tv_points_count.text = tv_points_count.text.toString() + " " + it.pointsCount.toString()
        })
        viewModel.getUser(userId)
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}
