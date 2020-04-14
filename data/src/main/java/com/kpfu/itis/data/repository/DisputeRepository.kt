package com.kpfu.itis.data.repository

import com.kpfu.itis.data.model.DisputeLocal

interface DisputeRepository {

    fun getDispute(id: Int): DisputeLocal

    fun getDisputes(): List<DisputeLocal>
}