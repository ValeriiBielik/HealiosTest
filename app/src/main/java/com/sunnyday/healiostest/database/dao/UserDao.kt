package com.sunnyday.healiostest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunnyday.healiostest.database.entity.User

@Dao
interface UserDao {

    @Query("Select * from user_table WHERE id = :userId")
    fun getUser(userId: Int): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}