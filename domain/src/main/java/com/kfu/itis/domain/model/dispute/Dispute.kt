package com.kfu.itis.domain.model.dispute

data class Dispute(
    var id: String,
    var title: String,
    var description: String,
    var ownerId: String,
    var type: DisputeType,
    var positions: String,
    var firstPosVoicesCount: Int,
    var secondPosVoicesCount: Int,
    var isFinished: Boolean,
    var tag: String
)