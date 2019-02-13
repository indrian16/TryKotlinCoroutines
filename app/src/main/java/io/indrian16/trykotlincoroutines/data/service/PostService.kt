package io.indrian16.trykotlincoroutines.data.service

import io.indrian16.trykotlincoroutines.data.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    fun getPosts(): Deferred<Response<List<Post>>>
}