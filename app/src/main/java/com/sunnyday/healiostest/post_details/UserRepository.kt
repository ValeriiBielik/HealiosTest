package com.sunnyday.healiostest.post_details

import com.sunnyday.healiostest.database.dao.UserDao
import com.sunnyday.healiostest.database.entity.User

class UserRepository(
    private val dao: UserDao,
    postId: Int
) {

    val user = dao.getUser(postId)

    suspend fun insertAll(users: List<User>) {
        for (user: User in users) {
            dao.insert(user)
        }
    }

}