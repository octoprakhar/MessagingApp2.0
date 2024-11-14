package com.example.messagesendingapp.authentication.presentation.signIn

import com.example.messagesendingapp.authentication.presentation.NavigationEvent

data class SignInState(
    val email : String = "",
    val password: String = "",
    val navigationEvent: NavigationEvent = NavigationEvent.None
)
