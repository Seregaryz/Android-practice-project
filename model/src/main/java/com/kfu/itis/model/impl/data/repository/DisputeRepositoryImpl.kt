package com.kfu.itis.model.impl.data.repository

import com.kfu.itis.model.api.domain.interfaces.DisputeRepository
import com.kfu.itis.model.api.domain.model.Dispute
import com.kfu.itis.model.api.domain.model.DisputeType
import com.kfu.itis.model.impl.data.network.DisputeApi
import javax.inject.Inject

class DisputeRepositoryImpl @Inject constructor(
    private val api: DisputeApi
) : DisputeRepository {


    override fun getDisputes(): List<Dispute> {
        return ArrayList<Dispute>()
    }

    override fun getDispute(id: Int): Dispute {
        return Dispute(1, "1", DisputeType.SPECIAL, ArrayList<String>())
    }


}