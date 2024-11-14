package com.example.messagesendingapp.authentication.presentation.registration

sealed interface RegistrationActions {
    data class SetUserName(val userName: String) : RegistrationActions
    data class SetPhoneNumber(val phoneNumber: String) : RegistrationActions
    data class SetEmail(val email: String) : RegistrationActions
    data class SetPassword(val password: String) : RegistrationActions
    data object RegisterUser : RegistrationActions
    data object onSignInClicked : RegistrationActions
}