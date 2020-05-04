package com.kpfu.itis.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kpfu.itis.core_db.model.UserLocal

@Dao
abstract class UserDAO {

    @Query("select * from users")
    abstract fun getUsers(): List<UserLocal>

    @Query("select * from users where id =:id")
    abstract fun getUser(id: String): UserLocal?

    @Insert
    abstract fun save(userLocal: UserLocal)
}