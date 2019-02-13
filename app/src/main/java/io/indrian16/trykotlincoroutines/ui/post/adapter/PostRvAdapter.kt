package io.indrian16.trykotlincoroutines.ui.post.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.indrian16.trykotlincoroutines.R
import io.indrian16.trykotlincoroutines.data.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostRvAdapter(private val listener: PostOnClickListener) : RecyclerView.Adapter<PostRvAdapter.PostHolder>() {

    private var postList: List<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) = holder.bind(postList[position])

    fun add(postList: List<Post>) {

        this.postList = postList
        notifyDataSetChanged()
    }

    inner class PostHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) = itemView.apply {

            postTitle.text = post.title

        }.setOnClickListener { listener.onClickPost(post) }
    }

    interface PostOnClickListener {

        fun onClickPost(post: Post)
    }
}