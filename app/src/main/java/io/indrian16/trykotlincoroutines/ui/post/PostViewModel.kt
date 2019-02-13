package io.indrian16.trykotlincoroutines.ui.post

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import io.indrian16.trykotlincoroutines.data.model.Post
import io.indrian16.trykotlincoroutines.data.repository.Repository
import kotlinx.coroutines.*

class PostViewModel(private val repository: Repository) : ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val mutablePost = MutableLiveData<List<Post>>()
        .apply { value = emptyList() }
    private val mutableLoading = MutableLiveData<Boolean>()
        .apply { value = false }
    private val mutableErrorMsg = MutableLiveData<String>()
        .apply { value = "Error: " }

    val postList: LiveData<List<Post>> = mutablePost
    val loading: LiveData<Boolean> = mutableLoading
    val errorMessage: LiveData<String> = mutableErrorMsg

    fun getPosts() {

        mutableLoading.postValue(true)
        uiScope.launch {

            try {

                val data = repository.getPosts()
                d { "[GET] ${data?.size} size" }
                mutablePost.postValue(data)
                mutableLoading.postValue(false)
            } catch (e: Exception) {

                mutableErrorMsg.postValue("Error: ${e.localizedMessage}")
                e { e.localizedMessage }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}