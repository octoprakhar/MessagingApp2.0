package com.example.messagesendingapp.authentication.data.Networking.dto

data class RegistrationDto(
    val userName : String,
    val phoneNumber : String,
    val email : String,
    val password: String,
    val userToken: String
)
