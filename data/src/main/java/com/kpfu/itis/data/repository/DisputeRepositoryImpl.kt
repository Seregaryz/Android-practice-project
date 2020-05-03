package com.kpfu.itis.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.data.mappers.DisputeMapper
import javax.inject.Inject


class DisputeRepositoryImpl @Inject constructor(
    private val disputeDAO: DisputeDAO
) : DisputeRepository {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("dispute")

    override fun getDisputes(): List<Dispute> {
        return DisputeMapper.toDisputeList(disputeDAO.getDisputes());
    }

    override fun getDispute(id: Int): Dispute {
        return DisputeMapper.toDispute(disputeDAO.getDispute(id));
    }

}