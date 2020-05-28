package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfu.itis.domain.interactor.DisputeInteractor
import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisputeViewModel @Inject constructor(
    private val disputeInteractor: DisputeInteractor
) : BaseViewModel() {

    private val disputeMutableLiveData = MutableLiveData<Dispute>()
    val disputeLiveData: LiveData<Dispute> = disputeMutableLiveData
    var key: String = "null"

    fun getDispute(disputeId: String): Boolean {
        var isFinished = false
        disposables.add(
            disputeInteractor.getDisputeFromDb(disputeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputeMutableLiveData.value = it
                    key = it.id
                }, {
                    it.printStackTrace()
                })
        )
        disposables.add(
            disputeInteractor.getDisputeFromFb(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFinished = it.isFinished
                }, {
                    it.printStackTrace()
                })
        )
        return isFinished
    }

    fun vote(isPlus: Boolean) {
        val updatedDispute = disputeLiveData.value
        if (isPlus) {
            disputeLiveData.value?.firstPosVoicesCount?.plus(1)
        } else disputeLiveData.value?.firstPosVoicesCount?.minus(1)
        if (updatedDispute?.firstPosVoicesCount!! >= 50) {
            updatedDispute.isFinished = true
        }
        disposables.add(
            disputeInteractor.vote(updatedDispute)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
        getDispute(updatedDispute.id)
    }


    fun updateView() {

    }
}