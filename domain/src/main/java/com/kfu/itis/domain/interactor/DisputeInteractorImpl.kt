package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): Observable<List<Dispute>> {
        return disputeRepository.getDisputesFromFb()
    }

    override fun getDisputeFromDb(id: String): Observable<Dispute> {
        return disputeRepository.getDisputeFromDb(id);
    }

    override fun getDisputeFromFb(id: String): Observable<Dispute> {
        return disputeRepository.getDisputeFromFb(id)
    }

    override fun createDisputeInFirebaseDd(
        title: String, description1: String,
        description2: String, disputeType: String
    ): String {
        return disputeRepository.createDisputeInFirebase(
            title,
            description1,
            description2,
            disputeType
        )
    }

    override fun vote(dispute: Dispute): Completable {
        return disputeRepository.updateDispute(dispute)
    }

    override fun createDisputeInLocalBd(
        id: String, title: String, description1: String,
        description2: String, disputeType: String
    ): Completable {
        return disputeRepository.createDisputeInLocalBd(
            id,
            title,
            description1,
            description2,
            disputeType
        )
    }

}
