package com.example.myapp1.fragments.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp1.databinding.ListItemBinding
import com.example.myapp1.fragments.home.models.UsersRespose


class MainAdapter(private val context: Context):RecyclerView.Adapter<MainAdapter.MyViewHolder> (){

    private var userResponse:UsersRespose? = null

    fun setUserResponse(userResponse:UsersRespose){
        this.userResponse = userResponse
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.binding.fullName.text = userResponse?.users?.get(position)?.firstName!!
//        Glide.with(context).load(userResponse?.users?.get(position)?.image!!).into(holder.binding.userImageView);

    }

    override fun getItemCount(): Int {
        return if (userResponse == null) 0
        else userResponse?.users?.size!!
    }

    class MyViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root)

}