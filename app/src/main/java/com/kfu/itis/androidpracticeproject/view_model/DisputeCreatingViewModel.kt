package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfu.itis.domain.interactor.DisputeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisputeCreatingViewModel @Inject constructor(
    private val disputeInteractor: DisputeInteractor
) : BaseViewModel() {

    private var disputeMutableLiveData = MutableLiveData<String>()
    var disputeLiveData: LiveData<String> = disputeMutableLiveData

    fun createDispute(
        title: String, description1: String,
        description2: String, disputeType: String
    ) {
        val key = disputeInteractor.createDisputeInFirebaseDd(
            title,
            description1,
            description2,
            disputeType
        )
        disposables.add(
            disputeInteractor.createDisputeInLocalBd(
                key,
                title,
                description1,
                description2,
                disputeType
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputeMutableLiveData.value = key
                }, {
                    it.printStackTrace()
                })
        )
    }
}