package com.example.myapp1.fragments.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp1.fragments.home.repository.UserRepository
import com.example.myapp1.fragments.login.viewmodels.LoginViewModel

class HomeViewModelFactory(private val userRepository: UserRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(userRepository) as T
    }

}