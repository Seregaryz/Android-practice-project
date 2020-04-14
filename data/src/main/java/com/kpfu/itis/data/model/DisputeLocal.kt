package com.kpfu.itis.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "disputes")
data class DisputeLocal(
    @PrimaryKey
    val id: Int,
    val ownerId: Int,
    val title: String,
    val descriptions: List<String>,
    val type: String
)