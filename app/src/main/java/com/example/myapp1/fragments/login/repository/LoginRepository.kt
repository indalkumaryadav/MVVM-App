package com.example.myapp1.fragments.login.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapp1.fragments.login.api.LoginApi
import com.example.myapp1.fragments.login.models.LoginRequest
import com.example.myapp1.fragments.login.models.LoginResponse
import com.example.myapp1.retrofit.NetworkResult
import retrofit2.Response


class LoginRepository(private val loginApi: LoginApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()

    val userResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _userResponseLiveData

    suspend fun login(loginRequest: LoginRequest){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = loginApi.login(loginRequest)
        handleResponse(response)

    }


    private fun handleResponse(response: Response<LoginResponse>) {

        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

        }
        else if(response.errorBody()!=null){
            // val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error("Error"))
        }
        else{
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

    }
}