package com.sunnyday.healiostest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunnyday.healiostest.database.entity.Post

@Dao
interface PostDao {

    @Query("Select * from post_table ORDER BY id ASC")
    fun getPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: Post)

    @Query("DELETE FROM post_table WHERE id = :id")
    suspend fun deletePost(id: Int)

    @Query("DELETE FROM post_table")
    suspend fun deleteAll()
}