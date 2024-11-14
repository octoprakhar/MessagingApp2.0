package com.example.messagesendingapp.authentication.presentation

//This only handles events related to the navigation
 sealed interface NavigationEvent {
     data object ToRegistrationScreen: NavigationEvent
    data object ToSignInScreen : NavigationEvent
    data object ToHomeScreen : NavigationEvent
    data object None : NavigationEvent // Reset state
}