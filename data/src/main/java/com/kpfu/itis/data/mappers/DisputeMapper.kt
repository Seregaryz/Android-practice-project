package com.kpfu.itis.data.mappers

import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.model.dispute.DisputeType
import com.kpfu.itis.core_db.model.DisputeLocal

class DisputeMapper {

    companion object {
        fun toDispute(disputeLocal: DisputeLocal): Dispute {
            return Dispute(
                disputeLocal.id,
                disputeLocal.title,
                disputeLocal.ownerId,
                DisputeType.valueOf(disputeLocal.type),
                disputeLocal.descriptions,
                disputeLocal.firstPosVoicesCount,
                disputeLocal.secondPosVoicesCount,
                disputeLocal.isFinished
            )
        }

        fun toDisputeLocal(dispute: Dispute): DisputeLocal {
            return DisputeLocal(
                dispute.id,
                dispute.creatorId,
                dispute.title,
                dispute.descriptions,
                dispute.type.name,
                dispute.firstPosVoicesCount,
                dispute.secondPosVoicesCount,
                dispute.isFinished
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