package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Completable
import io.reactivex.Observable

interface DisputeRepository {

    fun getDisputeFromDb(id: String): Observable<Dispute>

    fun getDisputeFromFb(id: String): Observable<Dispute>

    fun getDisputes(): Observable<List<Dispute>>

    fun getDisputesFromFb(): Observable<List<Dispute>>

    fun createDisputeInLocalBd(
        id: String,
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): Completable

    fun createDisputeInFirebase(
        title: String,
        description1: String,
        description2: String,
        disputeType: String
    ): String

    fun updateDispute(dispute: Dispute): Completable
}