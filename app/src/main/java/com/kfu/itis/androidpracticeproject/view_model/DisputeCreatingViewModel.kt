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
        title: String, description: String, position1: String,
        position2: String, disputeType: String, tag: String
    ) {
        val key = disputeInteractor.createDisputeInFirebaseDd(
            title,
            description,
            position1,
            position2,
            disputeType,
            tag
        )
        disposables.add(
            disputeInteractor.createDisputeInLocalBd(
                key,
                title,
                description,
                position1,
                position2,
                disputeType,
                tag
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
