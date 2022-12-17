package com.example.myapp1.fragments.home.api

import com.example.myapp1.fragments.home.models.SingleUser
import com.example.myapp1.fragments.home.models.UsersRespose
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("/users")
    suspend fun getAllUsers() : Response<UsersRespose>
}