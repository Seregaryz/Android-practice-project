package com.kpfu.itis.core_db.dao

import androidx.room.Dao
import androidx.room.Query
import com.kpfu.itis.data.model.DisputeLocal

@Dao
abstract class DisputeDAO {

    @Query("select * from disputes")
    abstract fun getDisputes(): List<DisputeLocal>

    @Query("select * from disputes where id = :id")
    abstract fun getDispute(id: Int): DisputeLocal
}