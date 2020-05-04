package com.kpfu.itis.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kpfu.itis.core_db.model.DisputeLocal
import io.reactivex.Single

@Dao
abstract class DisputeDAO {

    @Query("select * from disputes")
    abstract fun getDisputes(): Single<List<DisputeLocal>>

    @Query("select * from disputes where id = :id")
    abstract fun getDispute(id: Long): Single<DisputeLocal>

    @Insert
    abstract fun insertDispute(disputeLocal: DisputeLocal): Single<Long>
}