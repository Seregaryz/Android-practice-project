package com.kpfu.itis.data.mappers

import com.google.firebase.auth.FirebaseUser
import com.kfu.itis.domain.model.user.User
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
                0,
                true
            )
        }

        fun toUserFromFirebaseUser(
            fbUser: FirebaseUser,
            voicesCount: Int, winsCount: Int, pointsCount: Int
        ): User {
            return User(
                fbUser.uid,
                fbUser.displayName ?: "Unknown",
                fbUser.email ?: "Unknown",
                voicesCount,
                winsCount,
                pointsCount,
                true
            )
        }

        fun toLocalUserFromUser(user: User): UserLocal {
            return UserLocal(
                user.id,
                user.username,
                user.email,
                user.voicesCount,
                user.winCount,
                user.pointsCount,
                user.isAuthorized
            )
        }

        fun toUser(localUser: UserLocal): User {
            return User(
                localUser.id,
                localUser.username,
                localUser.email,
                localUser.voicesCount,
                localUser.winCount,
                localUser.pointsCount,
                localUser.isAuthorized
            )
        }
    }
}