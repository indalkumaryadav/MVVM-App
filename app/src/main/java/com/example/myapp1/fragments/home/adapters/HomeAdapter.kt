package com.example.myapp1.fragments.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp1.databinding.UsersItemBinding
import com.example.myapp1.fragments.home.models.UsersRespose

class HomeAdapter(private val context: Context):RecyclerView.Adapter<HomeAdapter.MyViewHolder> (){

    private var userResponse:UsersRespose? = null

    fun setUserResponse(userResponse:UsersRespose){
        this.userResponse = userResponse
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UsersItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.fullName.text = userResponse?.users?.get(position)?.firstName!!
        Glide.with(context).load(userResponse?.users?.get(position)?.image!!).into(holder.binding.userImageView);

    }

    override fun getItemCount(): Int {
        if (userResponse == null) return 0
        else return userResponse?.users?.size!!
    }

    class MyViewHolder(val binding:UsersItemBinding):RecyclerView.ViewHolder(binding.root)

}