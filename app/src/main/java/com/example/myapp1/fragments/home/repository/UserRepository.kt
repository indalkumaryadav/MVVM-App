package com.example.myapp1.fragments.home.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapp1.fragments.home.api.UserApi
import com.example.myapp1.fragments.home.models.UsersRespose
import com.example.myapp1.fragments.login.models.LoginResponse
import com.example.myapp1.retrofit.NetworkResult
import retrofit2.Response

class UserRepository(private val userApi: UserApi) {

    private val _userData = MutableLiveData<NetworkResult<UsersRespose>>()

    val userData: LiveData<NetworkResult<UsersRespose>>
        get() = _userData


    suspend fun getAllUsers(){
        _userData.postValue(NetworkResult.Loading())
        val response = userApi.getAllUsers()
        handleResponse(response)
    }


    private fun handleResponse(response: Response<UsersRespose>) {

        if (response.isSuccessful && response.body() != null) {
            _userData.postValue(NetworkResult.Success(response.body()!!))

        }
        else if(response.errorBody()!=null){
            // val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userData.postValue(NetworkResult.Error("Error"))
        }
        else{
            _userData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

    }
}