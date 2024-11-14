package com.example.messagesendingapp.authentication.presentation

import com.example.messagesendingapp.core.domain.util.errors.LocalStorageError
import com.example.messagesendingapp.core.domain.util.errors.NetworkError
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError

/*This is the interface that only triggers one time events other than navigation.

 */
sealed interface AuthenticationEvent {

    // Events for error messages
     data class NetworkErrorInRegistration(val error: NetworkError) : AuthenticationEvent
    data class LocalStorageErrorInRegistration(val error: LocalStorageError) : AuthenticationEvent
    data class OtherRegistrationRelatedErrors(val error : RegistrationOrSignInError) :
        AuthenticationEvent





}