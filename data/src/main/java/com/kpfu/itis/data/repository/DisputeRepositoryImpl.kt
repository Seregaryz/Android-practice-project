package com.kpfu.itis.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import com.kfu.itis.domain.reposirory.UserRepository
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.core_db.model.DisputeLocal
import com.kpfu.itis.data.mappers.DisputeMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DisputeRepositoryImpl @Inject constructor(
    private val disputeDAO: DisputeDAO,
    private val userRepository: UserRepository,
    private val database: FirebaseDatabase
) : DisputeRepository {

    var myRef = database.getReference("dispute")
    private var disputesMutableLiveData = MutableLiveData<List<DisputeLocal>>()
    val disputesLiveData: LiveData<List<DisputeLocal>> = disputesMutableLiveData

    private var disputeMutableLiveData = MutableLiveData<DisputeLocal>()
    val disputeLiveData: LiveData<DisputeLocal> = disputeMutableLiveData

    private val disposables = CompositeDisposable()

    override fun getDisputes(): List<Dispute>? {
        disposables.add(
            disputeDAO.getDisputes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputesMutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
        return disputesMutableLiveData.value?.let { DisputeMapper.toDisputeList(it) }
    }


    override fun createDisputeInLocalBd(
        title: String, description1: String,
        description2: String, disputeType: String
    ): Single<Long> {
        val currentUserId = userRepository.getCurrentUserId()
        val descriptions = description1 + "_" + description2
        val disputeLocal = DisputeLocal(null, currentUserId, title, descriptions, disputeType)
        return disputeDAO.insertDispute(disputeLocal)
    }

    override fun createDisputeInFirebase(
        id: Long, title: String, description1: String,
        description2: String, disputeType: String
    ): Boolean {
        val currentUserId = userRepository.getCurrentUserId()
        val descriptions = description1 + "_" + description2
        return myRef.push()
            .setValue(DisputeLocal(id, currentUserId, title, descriptions, disputeType)).isComplete
    }


    override fun getDispute(id: Long): Dispute? {
        disposables.add(
            disputeDAO.getDispute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disputeMutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        )
        return disputeMutableLiveData.value?.let { DisputeMapper.toDispute(it) };
    }

}