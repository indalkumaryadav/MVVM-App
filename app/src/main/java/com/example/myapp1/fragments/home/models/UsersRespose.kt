package com.example.myapp1.fragments.home.models

data class UsersRespose(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)