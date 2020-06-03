package com.kfu.itis.domain.interactor

import com.kfu.itis.domain.model.dispute.Dispute
import io.reactivex.Completable
import io.reactivex.Observable


interface DisputeInteractor {

    fun getDisputes(): Observable<List<Dispute>>

    fun saveDisputes(list: List<Dispute>): Completable

    fun getDisputeFromDb(id: String): Observable<Dispute>

    fun getDisputeFromFb(id: String): Observable<Dispute>

    fun createDisputeInLocalBd(
        id: String,
        title: String,
        description: String,
        position1: String,
        position2: String,
        disputeType: String,
        tag: String
    ): Completable

    fun createDisputeInFirebaseDd(
        title: String,
        description: String,
        position1: String,
        position2: String,
        disputeType: String,
        tag: String
    ): String

    fun getTagColor(tag: String): Int

    fun vote(dispute: Dispute, key: String): Completable
}