package com.kpfu.itis.core_db.dao

import androidx.room.*
import com.kpfu.itis.core_db.model.UserLocal
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class UserDAO {

    @Query("select * from users")
    abstract fun getUsers(): List<UserLocal>

    @Query("select * from users where id =:userId")
    abstract fun getUser(userId: String): Observable<UserLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(userLocal: UserLocal): Completable

    @Update
    abstract fun updateUser(userLocal: UserLocal): Completable
}
