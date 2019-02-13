package io.indrian16.trykotlincoroutines.data.repository

import io.indrian16.trykotlincoroutines.data.model.Post
import io.indrian16.trykotlincoroutines.data.service.PostService

class Repository(private val postService: PostService) : BaseRepository() {

    suspend fun getPosts(): List<Post>? {

        return safeApiCall(

                call = { postService.getPosts().await() },
                errorMessage = "Error Fetching Post"
        )
    }
}