package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.api.domain.model.Dispute

interface DisputeInteractor {

    fun getDisputes(): List<Dispute>

    fun getDispute(id: Int): Dispute
}