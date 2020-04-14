package com.kpfu.itis.data.repository

import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.data.model.DisputeLocal

class DisputeRepositoryImpl(
    private val disputeDAO: DisputeDAO
) : DisputeRepository {

    override fun getDisputes(): List<DisputeLocal> {
        return disputeDAO.getDisputes()
    }

    override fun getDispute(id: Int): DisputeLocal {
        return disputeDAO.getDispute(id)
    }

}