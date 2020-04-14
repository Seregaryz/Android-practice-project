package com.kpfu.itis.data.repository

import com.kpfu.itis.data.model.UserLocal

interface UserRepository {
    fun getUser(id: Int): UserLocal

    fun getUsers(): List<UserLocal>
}