package com.sunnyday.healiostest.post_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunnyday.healiostest.databinding.ItemCommentBinding
import com.sunnyday.healiostest.database.entity.Comment

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    private val dataList = arrayListOf<Comment>()

    fun setData(dataList: List<Comment>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mBinding: ItemCommentBinding

        constructor(binding: ItemCommentBinding) : this(binding.root) {
            mBinding = binding
        }

        fun bind(comment: Comment) {
            mBinding.tvTitle.text = comment.name
            mBinding.tvUser.text = comment.email
        }
    }
}