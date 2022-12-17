package com.example.myapp1.fragments.login.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp1.fragments.login.models.LoginRequest
import com.example.myapp1.fragments.login.models.LoginResponse
import com.example.myapp1.fragments.login.repository.LoginRepository
import com.example.myapp1.retrofit.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository):ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = loginRepository.userResponseLiveData


    fun login(loginRequest: LoginRequest){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.login(loginRequest)
        }
    }

}