package com.example.myapp1.fragments.login.api

import com.example.myapp1.fragments.login.models.LoginRequest
import com.example.myapp1.fragments.login.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApi {

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>



}