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

    private var disputeMutableLiveData = MutableLiveData<Long>()
    var disputeLiveData: LiveData<Long> = disputeMutableLiveData

    fun createDispute(
        title: String, description1: String,
        description2: String, disputeType: String
    ) {
        disposables.add(
            disputeInteractor.createDisputeInLocalBd(title, description1, description2, disputeType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputeMutableLiveData.value = it
                    disputeInteractor.createDisputeInFirebaseDd(
                        it,
                        title,
                        description1,
                        description2,
                        disputeType
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }
}