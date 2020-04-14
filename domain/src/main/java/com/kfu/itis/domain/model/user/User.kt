package com.kfu.itis.domain.model.user

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val voicesCount: Int,
    val winCount: Int
)