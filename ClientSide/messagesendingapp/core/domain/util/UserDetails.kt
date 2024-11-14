package com.example.messagesendingapp.core.domain.util

//How user must look like while using authentication feature
data class UserDetails(
    val userName : String,
    val phoneNumber : String,
    val email : String,
    val password: String,
    val userToken: String
)


