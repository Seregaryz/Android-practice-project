package com.kfu.itis.domain.model.dispute

data class Dispute(
    var id: Int,
    var title: String,
    var creator: Int,
    var type: DisputeType,
    var descriptions: List<String>
)