package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.ViewModel
import com.kfu.itis.domain.interactor.DisputeInteractor
import javax.inject.Inject

class DisputeCreatingViewModel @Inject constructor(
    private val disputeInteractor: DisputeInteractor
) : ViewModel() {

}