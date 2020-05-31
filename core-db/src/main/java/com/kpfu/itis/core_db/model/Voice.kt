package com.kpfu.itis.core_db.model

import androidx.room.Entity

@Entity(tableName = "voices")
data class Voice(
    var userId: String,
    var disputeId: String
)