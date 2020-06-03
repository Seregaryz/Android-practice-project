package com.kfu.itis.domain.reposirory

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Completable
import io.reactivex.Observable

interface DisputeRepository {

    fun getDisputeFromDb(id: String): Observable<Dispute>

    fun getDisputeFromFb(id: String): Observable<Dispute>

    fun getDisputes(): Observable<List<Dispute>>

    fun saveDisputes(list: List<Dispute>): Completable

    fun getDisputesFromFb(): Observable<List<Dispute>>

    fun createDisputeInLocalBd(
        id: String,
        title: String,
        description: String,
        position1: String,
        position2: String,
        disputeType: String,
        tag: String
    ): Completable

    fun createDisputeInFirebase(
        title: String,
        description: String,
        position1: String,
        position2: String,
        disputeType: String,
        tag: String
    ): String

    fun updateDispute(dispute: Dispute, key: String): Completable
}