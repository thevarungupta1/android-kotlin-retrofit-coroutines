package com.thevarungupta.kotlin_coroutines_retrofit_demo.data.repository

import com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getPosts() = apiHelper.getPosts()
}