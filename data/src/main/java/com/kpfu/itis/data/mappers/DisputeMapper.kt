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
                disputeLocal.description,
                disputeLocal.ownerId,
                DisputeType.valueOf(disputeLocal.type),
                disputeLocal.positions,
                disputeLocal.firstPosVoicesCount,
                disputeLocal.secondPosVoicesCount,
                disputeLocal.isFinished,
                disputeLocal.tag
            )
        }

        fun toDisputeLocal(dispute: Dispute): DisputeLocal {
            return DisputeLocal(
                dispute.id,
                dispute.ownerId,
                dispute.title,
                dispute.description,
                dispute.positions,
                dispute.type.name,
                dispute.firstPosVoicesCount,
                dispute.secondPosVoicesCount,
                dispute.isFinished,
                dispute.tag
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