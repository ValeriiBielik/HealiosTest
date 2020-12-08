package com.sunnyday.healiostest.post_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sunnyday.healiostest.database.DatabaseService
import com.sunnyday.healiostest.database.entity.Comment
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.database.entity.User
import com.sunnyday.healiostest.network.Api
import com.sunnyday.healiostest.network.util.ApiSuccessResponse
import kotlinx.coroutines.launch

class PostDetailsViewModel(application: Application, val post: Post) :
    AndroidViewModel(application) {

    private val apiService = Api.create()
    private val allComments: LiveData<List<Comment>>
    private val user: LiveData<User>
    private val userRepository: UserRepository =
        UserRepository(DatabaseService.getDatabase(application).userDao(), post.userId)
    private val commentsRepository: CommentsRepository =
        CommentsRepository(DatabaseService.getDatabase(application).commentDao(), post.id)

    init {
        allComments = commentsRepository.allComments
        user = userRepository.user
    }

    fun loadUserData() {
        apiService.getUser(post.userId).observeForever {
            if (it is ApiSuccessResponse) {
                viewModelScope.launch {
                    userRepository.insertAll(it.body)
                }
            }
        }
    }

    fun loadCommentsData() {
        apiService.getComments(post.id).observeForever {
            if (it is ApiSuccessResponse) {
                viewModelScope.launch {
                    commentsRepository.insertAll(it.body)
                }
            }
        }
    }

    fun getUserData() = user

    fun getCommentsData() = allComments
}