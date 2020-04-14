package com.kpfu.itis.data.network

import com.kpfu.itis.data.model.UserLocal

interface UserApi {

    fun getUser(id: Int): UserLocal

    fun getUsers(usersId: IntArray): List<UserLocal>
}
