package com.kpfu.itis.core_db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "disputes")
data class DisputeLocal(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var ownerId: String,
    var title: String,
    var descriptions: String,
    var type: String
)