package com.kfu.itis.domain.model.user

data class User(
    var id: String,
    var username: String,
    var email: String,
    var voicesCount: Int,
    var winCount: Int,
    var pointsCount: Int,
    var isAuthorized: Boolean
)
