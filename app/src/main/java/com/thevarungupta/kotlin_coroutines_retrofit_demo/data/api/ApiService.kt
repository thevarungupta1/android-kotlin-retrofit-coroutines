package com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api

import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): ArrayList<Post>
}