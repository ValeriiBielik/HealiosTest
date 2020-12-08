package com.sunnyday.healiostest.post_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunnyday.healiostest.database.entity.Post


class MyViewModelFactory(private val mApplication: Application, private val post: Post) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailsViewModel(mApplication, post) as T
    }
}