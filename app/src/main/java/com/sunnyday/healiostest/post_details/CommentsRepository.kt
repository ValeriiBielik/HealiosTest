package com.sunnyday.healiostest.post_details

import com.sunnyday.healiostest.database.dao.CommentDao
import com.sunnyday.healiostest.database.entity.Comment

class CommentsRepository(
    private val dao: CommentDao,
    postId: Int
) {

    val allComments = dao.getComments(postId)

    suspend fun insertAll(comments: List<Comment>) {
        for (comment: Comment in comments) {
            dao.insert(comment)
        }
    }

}