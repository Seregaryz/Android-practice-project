package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.model.mappers.DisputeMapper
import com.kpfu.itis.data.repository.DisputeRepository
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): List<Dispute> {
        return DisputeMapper.toDisputeList(disputeRepository.getDisputes())
    }

    override fun getDispute(id: Int): Dispute {
        return DisputeMapper.toDispute(disputeRepository.getDispute(id))
    }

}