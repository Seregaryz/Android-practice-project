package com.kfu.itis.domain.model.dispute

data class Dispute(
    var id: String,
    var title: String,
    var creatorId: String,
    var type: DisputeType,
    var descriptions: String,
    var firstPosVoicesCount: Int,
    var secondPosVoicesCount: Int,
    var isFinished: Boolean
)