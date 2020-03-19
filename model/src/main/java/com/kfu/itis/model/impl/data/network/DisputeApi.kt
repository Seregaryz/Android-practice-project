package com.kfu.itis.model.impl.data.network

import com.kfu.itis.model.api.domain.model.Dispute

interface DisputeApi {

    fun getDisputes(): List<Dispute>

    fun getDispute(id: Int): Dispute
}