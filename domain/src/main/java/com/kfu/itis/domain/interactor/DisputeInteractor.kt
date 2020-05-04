package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Single


interface DisputeInteractor {

    fun getDisputes(): List<Dispute>?

    fun getDispute(id: Long): Dispute?

    fun createDisputeInLocalBd(
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Single<Long>

    fun createDisputeInFirebaseDd(
        id: Long,
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Boolean
}