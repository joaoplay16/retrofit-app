package com.playlab.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.playlab.retrofitapp.adapter.MyAdapter
import com.playlab.retrofitapp.databinding.ActivityMainBinding
import com.playlab.retrofitapp.model.Post
import com.playlab.retrofitapp.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val myPost = Post(2,2, "Otimista-San", "Android Developer")
        viewModel.pushPost2(2,2, "Otimista-San", "Android Developer")
        viewModel.getCustomPosts(2, "id", "desc")
        viewModel.myResponse.observe(this){ response ->
            if(response.isSuccessful){
               Log.d("Main", response.body().toString())
               Log.d("Main", response.code().toString())
               Log.d("Main", response.message().toString())
            }else{
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setupRecyclerview(){
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}