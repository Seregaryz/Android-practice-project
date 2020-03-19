package com.kfu.itis.model.impl.domain

import com.kfu.itis.model.api.domain.interfaces.DisputeInteractor
import com.kfu.itis.model.api.domain.interfaces.DisputeRepository
import com.kfu.itis.model.api.domain.model.Dispute
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): List<Dispute> {
        return disputeRepository.getDisputes()
    }

    override fun getDispute(id: Int): Dispute {
        return disputeRepository.getDispute(id)
    }

}