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
        viewModel.myCustomPosts2.observe(this) { response ->
            if(response.isSuccessful){
                response.body()?.forEach{
                    Log.d("RESPONSE", it.body)
                    Log.d("RESPONSE", "---------------------")
                }
                binding.textView.text = response.body()?.toString()
            }else{
                Log.d("RESPONSE", response.errorBody().toString())
                binding.textView.text = response.code().toString()

            }
        }
        binding.button.setOnClickListener {
            val myNumber = binding.edtNumber.text.toString()
            val options: HashMap<String, String> = HashMap()
            options["_sort"] = "id"
            options["_order"] = "desc"
            viewModel.getCustomPosts2(Integer.parseInt(myNumber), options)
        }
    }
}