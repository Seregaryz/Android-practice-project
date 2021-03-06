package com.kpfu.itis.core_db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val username: String,
    val email: String,
    val voicesCount: Int,
    val winCount: Int,
    val pointsCount: Int,
    val isAuthorized: Boolean
) {

}