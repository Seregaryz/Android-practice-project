package com.kpfu.itis.data.mappers

import com.google.firebase.auth.FirebaseUser
import com.kpfu.itis.core_db.model.UserLocal

class UserMapper {

    companion object {
        fun toLocalUser(fbUser: FirebaseUser): UserLocal {
            return UserLocal(
                fbUser.uid,
                fbUser.displayName.toString(),
                fbUser.email.toString(),
                0,
                0,
                0
            )
        }
    }
}