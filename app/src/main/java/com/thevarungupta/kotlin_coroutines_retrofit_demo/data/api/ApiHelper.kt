package com.thevarungupta.kotlin_coroutines_retrofit_demo.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPosts() = apiService.getPosts()

}