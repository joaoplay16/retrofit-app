package com.playlab.retrofitapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playlab.retrofitapp.model.Post
import com.playlab.retrofitapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel(){

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response: Response<Post> = repository.getPost()
            myResponse.value = response
        }
    }
}