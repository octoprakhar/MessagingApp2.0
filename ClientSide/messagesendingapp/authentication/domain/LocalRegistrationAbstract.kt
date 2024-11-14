package com.example.messagesendingapp.authentication.domain

import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.UserDetails
import com.example.messagesendingapp.core.domain.util.errors.LocalStorageError

interface LocalRegistrationAbstract {
    //Inserting user in local database
    suspend fun insertUserLocally(userDetails: UserDetails): Result<Unit, LocalStorageError>
}