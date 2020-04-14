package com.kpfu.itis.data.model

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