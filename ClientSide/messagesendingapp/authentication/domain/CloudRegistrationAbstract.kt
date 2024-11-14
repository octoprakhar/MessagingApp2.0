package com.example.messagesendingapp.authentication.domain

import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.UserDetails

interface CloudRegistrationAbstract {

    //Register the user and return the result if error occurred or not
    suspend fun registerUserInFireStore(userDetails: UserDetails) : Result<Unit, Error>

    //We will register the user in firebase authentication
    suspend fun registerUserInAuth(userDetails: UserDetails) : Result<Unit,Error>





}