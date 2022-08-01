package com.playlab.retrofitapp.repository

import com.playlab.retrofitapp.api.RetrofitInstance
import com.playlab.retrofitapp.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}