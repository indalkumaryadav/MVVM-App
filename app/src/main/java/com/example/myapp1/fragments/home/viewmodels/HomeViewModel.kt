package com.example.myapp1.fragments.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp1.fragments.home.models.UsersRespose
import com.example.myapp1.fragments.home.repository.UserRepository
import com.example.myapp1.fragments.login.models.LoginResponse
import com.example.myapp1.retrofit.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository):ViewModel() {

    val userData: LiveData<NetworkResult<UsersRespose>>
        get() = userRepository.userData


    fun getAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getAllUsers()
        }
    }


}