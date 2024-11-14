package com.example.messagesendingapp.authentication.presentation.registration

import com.example.messagesendingapp.authentication.presentation.NavigationEvent

data class RegistrationState(
    val userName : String = "",
    val phoneNumber : String = "",
    val email : String = "",
    val password: String = "",
    val userToken: String = "",
    val isRegistering: Boolean = true,
    val navigationEvent: NavigationEvent = NavigationEvent.None
)
