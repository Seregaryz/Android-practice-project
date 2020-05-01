package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute


interface DisputeInteractor {

    fun getDisputes(): List<Dispute>

    fun getDispute(id: Int): Dispute
}