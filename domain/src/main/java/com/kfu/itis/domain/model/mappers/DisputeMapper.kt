package com.kfu.itis.domain.model.mappers

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.model.dispute.DisputeType
import com.kpfu.itis.data.model.DisputeLocal

class DisputeMapper {

    companion object {
        fun toDispute(disputeLocal: DisputeLocal): Dispute {
            return Dispute(
                disputeLocal.id,
                disputeLocal.title,
                disputeLocal.ownerId,
                DisputeType.valueOf(disputeLocal.type),
                disputeLocal.descriptions
            )
        }

        fun toDisputeList(list: List<DisputeLocal>): List<Dispute> {
            var result = ArrayList<Dispute>()
            for (d in list) {
                result.add(toDispute(d))
            }
            return result
        }
    }

}