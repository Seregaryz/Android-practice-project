package com.kpfu.itis.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kpfu.itis.core_db.model.DisputeLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class DisputeDAO {

    @Query("select * from disputes")
    abstract fun getDisputes(): Observable<List<DisputeLocal>>

    @Query("select * from disputes where id = :id")
    abstract fun getDispute(id: String): Observable<DisputeLocal>

    @Insert
    abstract fun insertDispute(disputeLocal: DisputeLocal): Completable

    @Update
    abstract fun updateDispute(disputeLocal: DisputeLocal): Completable
}