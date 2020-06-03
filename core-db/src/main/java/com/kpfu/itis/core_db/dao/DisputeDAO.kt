package com.kpfu.itis.core_db.dao

import androidx.room.*
import com.kpfu.itis.core_db.model.DisputeLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class DisputeDAO {

    @Query("select * from disputes")
    abstract fun getDisputes(): Observable<List<DisputeLocal>>

    @Query("select * from disputes where id = :id")
    abstract fun getDispute(id: String): Observable<DisputeLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDispute(disputeLocal: DisputeLocal): Completable

    @Update
    abstract fun updateDispute(disputeLocal: DisputeLocal): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDisputes(list: List<DisputeLocal>): Completable
}