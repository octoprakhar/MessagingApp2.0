package com.example.messagesendingapp.authentication.presentation.signIn

interface SignInAction {
    data class SetEmail(val email: String) : SignInAction
    data class SetPassword(val password: String) : SignInAction
    data object SignInUser : SignInAction
    data object onRegistrationClicked : SignInAction
}