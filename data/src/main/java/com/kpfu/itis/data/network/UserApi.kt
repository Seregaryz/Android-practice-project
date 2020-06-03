package com.kpfu.itis.data.network

import com.kpfu.itis.core_db.model.UserLocal

interface UserApi {

    fun getUser(id: Int): UserLocal

    fun getUsers(usersId: IntArray): List<UserLocal>
}

