package com.example.myapp1.retrofit

import com.example.myapp1.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object RetrofitHelper {

    fun  getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    }

}