package com.sunnyday.healiostest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunnyday.healiostest.database.entity.Comment

@Dao
interface CommentDao {

    @Query("Select * from comment_table WHERE postId = :postId ORDER BY id ASC")
    fun getComments(postId: Int): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comment: Comment)

    @Query("DELETE FROM comment_table WHERE id = :id")
    suspend fun deleteComment(id: Int)

    @Query("DELETE FROM comment_table")
    suspend fun deleteAll()
}