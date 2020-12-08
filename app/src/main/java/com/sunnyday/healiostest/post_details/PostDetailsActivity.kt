package com.sunnyday.healiostest.post_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyday.healiostest.R
import com.sunnyday.healiostest.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityPostDetailsBinding
    private lateinit var mViewModel: PostDetailsViewModel
    private val mAdapter = CommentsAdapter()

    companion object {
        const val PARAM_POST = "post"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_details)
        initViewModel()
        setContent()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(this.application, intent.getParcelableExtra(PARAM_POST)!!)
        ).get(PostDetailsViewModel::class.java)
        mViewModel.getUserData().observe(this, { user ->
            run {
                if (user == null)
                    mViewModel.loadUserData()
                else
                    mBinding.tvUserName.text = user.name
            }
        })
        mViewModel.getCommentsData().observe(this, { comments ->
            run {
                if (comments != null && comments.isEmpty())
                    mViewModel.loadCommentsData()
                mAdapter.setData(comments)
            }
        })
    }

    private fun setContent() {
        mBinding.tvTitle.text = mViewModel.post.title
        mBinding.tvDescription.text = mViewModel.post.body
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mBinding.rvComments.let {
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
}