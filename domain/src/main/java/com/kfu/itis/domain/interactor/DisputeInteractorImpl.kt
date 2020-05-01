package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): List<Dispute> {
        return disputeRepository.getDisputes();
    }

    override fun getDispute(id: Int): Dispute {
        return disputeRepository.getDispute(id);
    }

}