package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Completable
import io.reactivex.Observable


interface DisputeInteractor {

    fun getDisputes(): Observable<List<Dispute>>

    fun getDisputeFromDb(id: String): Observable<Dispute>

    fun getDisputeFromFb(id: String): Observable<Dispute>

    fun createDisputeInLocalBd(
        id: String,
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Completable

    fun createDisputeInFirebaseDd(
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): String

    fun vote(dispute: Dispute): Completable
}