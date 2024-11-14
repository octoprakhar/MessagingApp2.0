package com.example.messagesendingapp.core.presentation

sealed class Screens(val route : String) {
    data object RegistrationScreen : Screens("registration_screen")
    data object SignInScreen : Screens("sign_in_screen")
    data object HomeScreen : Screens("home_screen")


}