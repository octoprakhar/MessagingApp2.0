package com.example.messagesendingapp.authentication.domain

import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.UserDetails

interface SignInAbstract {

    //Just sign In the user and return the result if error occurred or not
    suspend fun signInUser(email: String, password: String) : Result<Unit, Error>
}