package com.sunnyday.healiostest.network

import androidx.lifecycle.LiveData
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.database.entity.Comment
import com.sunnyday.healiostest.database.entity.User
import com.sunnyday.healiostest.network.util.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getPosts(): LiveData<ApiResponse<ArrayList<Post>>>

    @GET("users")
    fun getUser(@Query("id") userId: Int): LiveData<ApiResponse<ArrayList<User>>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): LiveData<ApiResponse<ArrayList<Comment>>>
}