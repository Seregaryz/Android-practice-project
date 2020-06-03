package com.kpfu.itis.core_db.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "disputes")
data class DisputeLocal(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var id: String = "",
    var ownerId: String = "",
    var title: String = "",
    var description: String = "",
    var positions: String = "",
    var type: String = "",
    var firstPosVoicesCount: Int = 0,
    var secondPosVoicesCount: Int = 0,
    var isFinished: Boolean = true,
    var tag: String = ""
) : Serializable
