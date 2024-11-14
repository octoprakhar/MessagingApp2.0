package com.example.messagesendingapp.authentication.data.Networking

import com.example.messagesendingapp.authentication.domain.SignInAbstract
import com.example.messagesendingapp.core.data.networking.FirebaseAuthenticationHelper
import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result

class CloudSignIn(
    private val firebaseAuthenticationHelper: FirebaseAuthenticationHelper

): SignInAbstract {
    override suspend fun signInUser(email: String, password: String): Result<Unit, Error> {
        return firebaseAuthenticationHelper.SignInUser(
            email = email,
            password = password
        )
    }
}