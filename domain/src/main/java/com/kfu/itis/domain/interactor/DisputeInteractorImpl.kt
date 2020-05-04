package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import io.reactivex.Single
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): List<Dispute>? {
        return disputeRepository.getDisputes();
    }

    override fun getDispute(id: Long): Dispute? {
        return disputeRepository.getDispute(id);
    }

    override fun createDisputeInFirebaseDd(
        id: Long, title: String, description1: String,
        description2: String, disputeType: String
    ): Boolean {
        return disputeRepository.createDisputeInFirebase(
            id,
            title,
            description1,
            description2,
            disputeType
        )
    }

    override fun createDisputeInLocalBd(
        title: String, description1: String,
        description2: String, disputeType: String
    ): Single<Long> {
        return disputeRepository.createDisputeInLocalBd(
            title,
            description1,
            description2,
            disputeType
        )
    }

}
