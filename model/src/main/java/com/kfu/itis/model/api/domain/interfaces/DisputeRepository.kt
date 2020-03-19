package com.kfu.itis.model.api.domain.interfaces

import com.kfu.itis.model.api.domain.model.Dispute

interface DisputeRepository {

    fun getDisputes(): List<Dispute>

    fun getDispute(id: Int): Dispute
}