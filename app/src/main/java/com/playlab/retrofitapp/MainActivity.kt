package com.playlab.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.playlab.retrofitapp.databinding.ActivityMainBinding
import com.playlab.retrofitapp.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this) { response ->
           if(response.isSuccessful){
               Log.d("RESPONSE", response.body()?.id.toString())
               Log.d("RESPONSE", response.body()?.myUserId.toString())
               Log.d("RESPONSE", response.body()?.body.toString())
               binding.textView.text = response.body()?.body.toString()
           }else{
               Log.d("RESPONSE", response.errorBody().toString())
               binding.textView.text = response.errorBody().toString()

           }
        }
    }
}