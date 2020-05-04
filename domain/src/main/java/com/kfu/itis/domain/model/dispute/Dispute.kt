package com.kfu.itis.domain.model.dispute

data class Dispute(
    var id: Long,
    var title: String,
    var creatorId: String,
    var type: DisputeType,
    var descriptions: String
)