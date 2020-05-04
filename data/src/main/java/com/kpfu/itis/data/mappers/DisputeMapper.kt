package com.kpfu.itis.data.mappers

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.model.dispute.DisputeType
import com.kpfu.itis.core_db.model.DisputeLocal

class DisputeMapper {

    companion object {
        fun toDispute(disputeLocal: DisputeLocal): Dispute {
            return Dispute(
                disputeLocal.id ?: 0,
                disputeLocal.title,
                disputeLocal.ownerId,
                DisputeType.valueOf(disputeLocal.type),
                disputeLocal.descriptions
            )
        }

        fun toDisputeList(list: List<DisputeLocal>): List<Dispute> {
            val result = ArrayList<Dispute>()
            for (d in list) {
                result.add(toDispute(d))
            }
            return result
        }
    }

}