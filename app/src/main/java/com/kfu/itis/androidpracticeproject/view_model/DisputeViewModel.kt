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
    lateinit var currentDispute: Dispute
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

    fun vote(key: String): Boolean {
        var isSuccess = true
        disposables.add(
            disputeInteractor.vote(currentDispute, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { isSuccess = true }
                .doOnError { isSuccess = false }
                .subscribe()
        )
        getDispute(currentDispute.id)
        return isSuccess
    }


    fun updateView() {

    }

    companion object {
        const val IS_FIRST_PLUS_KEY = "first plus"
        const val IS_FIRST_MINUS_KEY = "first minus"
        const val IS_SECOND_PLUS_KEY = "second plus"
        const val IS_SECOND_MINUS_KEY = "second minus"
    }
}