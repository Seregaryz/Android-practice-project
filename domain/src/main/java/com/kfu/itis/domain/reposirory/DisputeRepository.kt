package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Single

interface DisputeRepository {

    fun getDispute(id: Long): Dispute?

    fun getDisputes(): List<Dispute>?

    fun createDisputeInLocalBd(
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Single<Long>

    fun createDisputeInFirebase(
        id: Long,
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Boolean
}