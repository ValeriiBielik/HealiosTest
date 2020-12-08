package com.sunnyday.healiostest.main

import androidx.lifecycle.LiveData
import com.sunnyday.healiostest.database.dao.PostDao
import com.sunnyday.healiostest.database.entity.Post

class PostsRepository(private val dao: PostDao) {

    val allPosts: LiveData<List<Post>> = dao.getPosts()

    suspend fun insertAll(posts: List<Post>) {
        for (post: Post in posts) {
            dao.insert(post)
        }
    }

}

