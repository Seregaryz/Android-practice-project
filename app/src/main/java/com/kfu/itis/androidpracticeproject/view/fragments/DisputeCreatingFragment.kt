package com.kfu.itis.androidpracticeproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kfu.itis.androidpracticeproject.App
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.DisputeCreatingViewModel
import kotlinx.android.synthetic.main.fragment_create_dispute.*
import javax.inject.Inject

class DisputeCreatingFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DisputeCreatingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.disputeCreatingComponent.inject(this)
        initViewModel()
        return inflater.inflate(R.layout.fragment_create_dispute, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val spinner: Spinner = disputes_type_spinner
        ArrayAdapter.createFromResource(
            App.appComponent.getContext(),
            R.array.disputes_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        val disputeType = parent.getItemAtPosition(pos).toString()
        Snackbar.make(view, disputeType, Snackbar.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(DisputeCreatingViewModel::class.java)
    }

}
