package com.playlab.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.playlab.retrofitapp.R
import com.playlab.retrofitapp.databinding.ActivityMainBinding
import com.playlab.retrofitapp.model.Post

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder (itemView: View) : RecyclerView
    .ViewHolder(itemView){
        val txtUserId: TextView = itemView.findViewById(R.id.txtUserId)
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtBody: TextView = itemView.findViewById(R.id.txtBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtUserId.text = myList[position].myUserId.toString()
        holder.txtId.text = myList[position].id.toString()
        holder.txtTitle.text = myList[position].title
        holder.txtBody.text = myList[position].body
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}