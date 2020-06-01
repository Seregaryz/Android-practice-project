package com.kpfu.itis.core_db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voices")
data class Voice(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var userId: String,
    var disputeId: String
)