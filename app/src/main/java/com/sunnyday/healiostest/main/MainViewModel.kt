package com.sunnyday.healiostest.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sunnyday.healiostest.database.DatabaseService
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.network.Api
import com.sunnyday.healiostest.network.util.ApiSuccessResponse
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val apiService = Api.create()
    private val allPosts: LiveData<List<Post>>
    private val repository: PostsRepository =
        PostsRepository(DatabaseService.getDatabase(application).postDao())

    init {
        allPosts = repository.allPosts
    }

    fun loadPostsData() {
        apiService.getPosts().observeForever {
            if (it is ApiSuccessResponse) {
                viewModelScope.launch {
                    repository.insertAll(it.body)
                }
            }
        }
    }

    fun getPostsData() = allPosts
}