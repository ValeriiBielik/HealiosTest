package com.sunnyday.healiostest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sunnyday.healiostest.database.dao.CommentDao
import com.sunnyday.healiostest.database.dao.PostDao
import com.sunnyday.healiostest.database.dao.UserDao
import com.sunnyday.healiostest.database.entity.Comment
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.database.entity.User

@Database(entities = [Post::class, Comment::class, User::class], version = 1, exportSchema = false)
abstract class DatabaseService : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "database"

        @Volatile
        private var INSTANCE: DatabaseService? = null

        fun getDatabase(context: Context): DatabaseService {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}