package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view.list.DisputeAdapter
import com.kfu.itis.androidpracticeproject.view_model.DisputeListViewModel
import kotlinx.android.synthetic.main.fragment_disputes.*
import javax.inject.Inject

class DisputesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DisputeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_disputes, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusDisputeListComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe(viewModel)
        initViews()
    }

    private fun initViews() {
        rv_disputes.layoutManager = LinearLayoutManager(context)
        rv_disputes.setHasFixedSize(true)
    }

    private fun subscribe(viewModel: DisputeListViewModel) {
        viewModel.disputesLiveData.observe(viewLifecycleOwner, Observer {
            if (rv_disputes.adapter == null) {
                rv_disputes.adapter = DisputeAdapter { disputeId -> navigateToDetails(disputeId) }
            }
            (rv_disputes.adapter as DisputeAdapter).submitList(it)
        })
        viewModel.getDisputes()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(DisputeListViewModel::class.java)
    }

    private fun navigateToDetails(id: String) {
        val bundle = Bundle()
        bundle.putString(DisputeFragment.DISPUTE_ID_KEY, id)
        this.view?.let { Navigation.findNavController(it).navigate(R.id.disputeFragment, bundle) }
    }
}
