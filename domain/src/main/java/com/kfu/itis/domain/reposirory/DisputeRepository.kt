package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.dispute.Dispute

interface DisputeRepository {

    fun getDispute(id: Int): Dispute

    fun getDisputes(): List<Dispute>
}