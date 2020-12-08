package com.sunnyday.healiostest.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyday.healiostest.R
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.databinding.ActivityMainBinding
import com.sunnyday.healiostest.post_details.PostDetailsActivity


class MainActivity : AppCompatActivity(), PostsAdapter.OnPostItemClickListener {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mBinding: ActivityMainBinding
    private val mAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mAdapter.setOnPostItemClickListener(this)
        setUpRecyclerView()
        mViewModel.getPostsData().observe(this, { posts ->
            run {
                if (posts.isEmpty())
                    mViewModel.loadPostsData()
                mAdapter.setData(posts)
            }
        })
    }

    private fun setUpRecyclerView() {
        mBinding.rvPosts.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            it.addItemDecoration(
                DividerItemDecoration(
                    this,
                    LinearLayoutManager.VERTICAL
                )
            )
            it.adapter = mAdapter
        }
    }

    override fun onPostItemClick(post: Post) {
        val starter = Intent(this, PostDetailsActivity::class.java)
        starter.putExtra(PostDetailsActivity.PARAM_POST, post)
        startActivity(starter)
    }
}