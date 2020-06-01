package com.kpfu.itis.core_db.dao


import androidx.room.Dao
import androidx.room.Query
import com.kpfu.itis.core_db.model.Voice
import io.reactivex.Observable

@Dao
abstract class VoiceDAO {

    @Query("select * from voices where userId = :userId")
    abstract fun getVoices(userId: String): Observable<List<Voice>>

}