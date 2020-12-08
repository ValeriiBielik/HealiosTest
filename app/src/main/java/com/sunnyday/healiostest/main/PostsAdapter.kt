package com.sunnyday.healiostest.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunnyday.healiostest.database.entity.Post
import com.sunnyday.healiostest.databinding.ItemPostBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val dataList = arrayListOf<Post>()
    private lateinit var onPostItemClickListener: OnPostItemClickListener

    fun setData(dataList: List<Post>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setOnPostItemClickListener(onPostItemClickListener: OnPostItemClickListener) {
        this.onPostItemClickListener = onPostItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(dataList[position], onPostItemClickListener)
    }

    override fun getItemCount() = dataList.size

    class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mBinding: ItemPostBinding

        constructor(binding: ItemPostBinding) : this(binding.root) {
            mBinding = binding
        }

        fun bind(post: Post, onPostItemClickListener: OnPostItemClickListener) {
            mBinding.title.text = post.title
            mBinding.root.setOnClickListener { onPostItemClickListener.onPostItemClick(post) }
        }
    }

    interface OnPostItemClickListener {
        fun onPostItemClick(post: Post)
    }
}