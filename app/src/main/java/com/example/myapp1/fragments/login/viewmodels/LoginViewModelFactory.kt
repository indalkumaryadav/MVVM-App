package com.example.myapp1.fragments.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp1.fragments.login.repository.LoginRepository

class LoginViewModelFactory(private val loginRepository: LoginRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}