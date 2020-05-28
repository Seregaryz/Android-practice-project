package com.kfu.itis.androidpracticeproject.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfu.itis.domain.interactor.DisputeInteractor
import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisputeListViewModel @Inject constructor(
    private val disputeInteractor: DisputeInteractor
) : BaseViewModel() {

    private val disputesMutableLiveData = MutableLiveData<List<Dispute>>()
    val disputesLiveData: LiveData<List<Dispute>> = disputesMutableLiveData

    fun getDisputes() {
        disposables.add(
            disputeInteractor.getDisputes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputesMutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
    }

}
