package com.kfu.itis.model.api.domain.model

data class Dispute(
    var id: Int,
    var title: String,
    var type: DisputeType,
    var descriptions: List<String>
)