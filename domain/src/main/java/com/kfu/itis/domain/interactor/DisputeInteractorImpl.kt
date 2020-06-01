package com.kfu.itis.domain.interactor

import android.graphics.Color
import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DisputeInteractorImpl @Inject constructor(
    private val disputeRepository: DisputeRepository
) : DisputeInteractor {

    override fun getDisputes(): Observable<List<Dispute>> {
        return disputeRepository.getDisputesFromFb()
    }

    override fun getDisputeFromDb(id: String): Observable<Dispute> {
        return disputeRepository.getDisputeFromDb(id);
    }

    override fun getDisputeFromFb(id: String): Observable<Dispute> {
        return disputeRepository.getDisputeFromFb(id)
    }

    override fun createDisputeInFirebaseDd(
        title: String, description: String, position1: String,
        position2: String, disputeType: String, tag: String
    ): String {
        return disputeRepository.createDisputeInFirebase(
            title,
            description,
            position1,
            position2,
            disputeType,
            tag
        )
    }

    override fun vote(dispute: Dispute, key: String): Completable {
        return disputeRepository.updateDispute(dispute, key)
    }

    override fun getTagColor(tag: String): Int {
        when (tag) {
            "Cars" -> return Color.parseColor("#C71585")
            "Geographic" -> return Color.parseColor("#00FF00")
            "Love" -> return Color.parseColor("#FF0000")
            "Politic" -> return Color.parseColor("#0000CD")
            "Sport" -> return Color.parseColor("#800080")
            "Cinema" -> return Color.parseColor("#00CED1")
            "Art" -> return Color.parseColor("#FF8C00")
        }
        return 0
    }

    override fun createDisputeInLocalBd(
        id: String, title: String, description: String, position1: String,
        position2: String, disputeType: String, tag: String
    ): Completable {
        return disputeRepository.createDisputeInLocalBd(
            id,
            title,
            description,
            position1,
            position2,
            disputeType,
            tag
        )
    }

}
