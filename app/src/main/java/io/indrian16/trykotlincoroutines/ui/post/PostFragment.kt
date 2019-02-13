package io.indrian16.trykotlincoroutines.ui.post

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.indrian16.trykotlincoroutines.R
import io.indrian16.trykotlincoroutines.data.model.Post
import io.indrian16.trykotlincoroutines.ui.post.adapter.PostRvAdapter
import io.indrian16.trykotlincoroutines.util.showToast
import kotlinx.android.synthetic.main.fragment_post.*
import org.koin.android.ext.android.inject

class PostFragment : Fragment(), PostRvAdapter.PostOnClickListener {

    private val viewModel: PostViewModel by inject()
    private val mAdapter = PostRvAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRv()
        initListener()

        viewModel.getPosts()
    }

    private fun initViewModel() {

        viewModel.postList.observe(this, Observer { observerPost(it!!) })
        viewModel.loading.observe(this, Observer { observerLoading(it!!) })
        viewModel.errorMessage.observe(this, Observer { observerError(it!!) })
    }

    private fun initRv() = rvPost.apply {

        layoutManager = LinearLayoutManager(context)
        adapter = mAdapter
    }

    private fun initListener() {

        swipePost.setOnRefreshListener {

            viewModel.getPosts()
        }
    }

    private fun observerPost(postList: List<Post>) {

        mAdapter.add(postList)
    }

    private fun observerLoading(loading: Boolean) {

        swipePost.isRefreshing = loading
    }

    private fun observerError(error: String) {

        swipePost.isRefreshing = false
        showToast(error)
    }

    override fun onClickPost(post: Post) {

        showToast("You click ${post.title}")
    }

    companion object {

        fun newInstance() = PostFragment()
    }

}
