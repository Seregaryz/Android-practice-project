package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.DisputeViewModel
import kotlinx.android.synthetic.main.fragment_dispute.*
import javax.inject.Inject

class DisputeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DisputeViewModel
    lateinit var disputeId: String
    var isButton1Chosen = false
    var isButton2Chosen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dispute, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusDisputeComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disputeId = arguments?.getString(DISPUTE_ID_KEY).toString()
        subscribe(viewModel, disputeId)
        initViews()
    }

    private fun subscribe(viewModel: DisputeViewModel, disputeId: String) {
        viewModel.disputeLiveData.observe(viewLifecycleOwner, Observer {
            if (viewModel.disputeLiveData.value?.isFinished == true) {
                //навигируемся на другой экран
            } else {
                showSnackBar("Get/Update")
                val positions = viewModel.disputeLiveData.value?.positions?.split("_")
                viewModel.currentDispute = viewModel.disputeLiveData.value!!
                tv_title.text = viewModel.disputeLiveData.value?.title
                val tag = viewModel.disputeLiveData.value?.tag ?: "error"
                tv_tag.text = tag
                tv_tag.setBackgroundColor(viewModel.getTagColor(tag))
                tv_description.text = viewModel.disputeLiveData.value?.description
                tv_first_position.text = positions?.get(0) ?: "First position"
                tv_description2.text = positions?.get(1) ?: "Second position"
            }
        })
        viewModel.getDispute(disputeId)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(DisputeViewModel::class.java)
    }

    private fun initViews() {
        btn_vote_desc1.setOnClickListener {
            if (!isButton1Chosen) {
                isButton1Chosen = true
                if (!voteAction(DisputeViewModel.IS_FIRST_PLUS_KEY)) {
                    //показать ошибку отправки данных
                } else {
                    btn_vote_desc1.text = BUTTON_CANCEL_VOTE
                    btn_vote_desc2.isClickable = false
                    showSnackBar("vote")
                    viewModel.getDispute(disputeId)
                }
            } else {
                isButton1Chosen = false
                if (!voteAction(DisputeViewModel.IS_FIRST_MINUS_KEY)) {
                    //показать ошибку отправки данных
                } else {
                    btn_vote_desc1.text = BUTTON_VOTE
                    btn_vote_desc2.isClickable = true
                    showSnackBar(viewModel.disputeLiveData.value?.firstPosVoicesCount.toString())
                    viewModel.getDispute(disputeId)
                }
            }
        }
        btn_vote_desc2.setOnClickListener {
            if (!isButton2Chosen) {
                isButton2Chosen = true
                if (!voteAction(DisputeViewModel.IS_SECOND_PLUS_KEY)) {
                    //показать ошибку отправки данных
                } else {
                    btn_vote_desc2.text = BUTTON_CANCEL_VOTE
                    btn_vote_desc1.isClickable = false
                    showSnackBar(viewModel.disputeLiveData.value?.secondPosVoicesCount.toString())
                    viewModel.getDispute(disputeId)
                }
            } else {
                isButton2Chosen = false
                if (!voteAction(DisputeViewModel.IS_SECOND_MINUS_KEY)) {
                    //показать ошибку отправки данных
                } else {
                    btn_vote_desc2.text = BUTTON_VOTE
                    btn_vote_desc1.isClickable = true
                    showSnackBar(viewModel.disputeLiveData.value?.secondPosVoicesCount.toString())
                    viewModel.getDispute(disputeId)
                }
            }
        }
    }

    private fun voteAction(key: String): Boolean {
        return viewModel.vote(key)
    }

    private fun showSnackBar(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
    }

    companion object {
        const val DISPUTE_ID_KEY = "disputeId"
        const val MESSAGE_VOICE_SENT = "Your voice has sent"
        const val BUTTON_CANCEL_VOTE = "Cancel vote"
        const val BUTTON_VOTE = "Vote"
    }

}
