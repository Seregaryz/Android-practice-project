package com.kpfu.itis.data.network

import com.kpfu.itis.core_db.model.DisputeLocal

interface DisputeApi {

    fun getDisputes(): List<DisputeLocal>

    fun getDispute(id: Int): DisputeLocal
}