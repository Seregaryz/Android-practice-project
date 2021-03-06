package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.androidpracticeproject.view_model.DisputeCreatingViewModel
import kotlinx.android.synthetic.main.fragment_create_dispute.*
import javax.inject.Inject

class DisputeCreatingFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: DisputeCreatingViewModel
    lateinit var disputeType: String
    lateinit var specialTag: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_dispute, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusDisputeCreatingComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val typeSpinner: Spinner = disputes_type_spinner
        ArrayAdapter.createFromResource(
            Injector.appComponent.getContext(),
            R.array.disputes_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            typeSpinner.adapter = adapter
        }
        val tagsSpinner: Spinner = disputes_special_tag_spinner
        ArrayAdapter.createFromResource(
            Injector.appComponent.getContext(),
            R.array.disputes_special_tags,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tagsSpinner.adapter = adapter
        }
        tagsSpinner.onItemSelectedListener = this
        btn_create_dispute.setOnClickListener {
            val title = et_dispute_title.text.toString()
            val description = et_dispute_description.text.toString()
            val position1 = et_dispute_description1.text.toString()
            val position2 = et_dispute_description2.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty() && position1.isNotEmpty() && position2.isNotEmpty()) {
                viewModel.disputeLiveData.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        this.view?.let { it1 ->
                            Navigation.findNavController(it1).navigate(R.id.disputes_list)
                        }
                    } else {
                        tv_errors.text = CONNECTION_ERROR
                        tv_errors.isVisible = true
                    }
                })
                viewModel.createDispute(
                    title,
                    description,
                    position1,
                    position2,
                    disputeType,
                    specialTag
                )
            } else {
                tv_errors.text = EMPTY_FIELDS_ERROR
                tv_errors.isVisible = true
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        disputeType = "Usual"
        specialTag = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DisputeCreatingViewModel::class.java)
    }

    companion object {
        const val EMPTY_FIELDS_ERROR = "You should edit all fields"
        const val CONNECTION_ERROR = "Error of connection"
    }

}
