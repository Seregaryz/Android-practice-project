package com.kpfu.itis.core_db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey val id: Int,
    val username: String,
    val email: String,
    val voicesCount: Int,
    val winCount: Int
)