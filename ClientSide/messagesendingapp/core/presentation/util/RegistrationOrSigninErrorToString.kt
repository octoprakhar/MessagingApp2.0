package com.example.messagesendingapp.core.presentation.util

import android.content.Context
import com.example.messagesendingapp.R
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError

fun RegistrationOrSignInError.toString(context: Context) : String{
    val resId = when(this){
        RegistrationOrSignInError.INVALID_EMAIL_ADDRESS -> R.string.InvalidEmailAddress
        RegistrationOrSignInError.INVALID_PASSWORD -> R.string.InvalidPassword
        RegistrationOrSignInError.INVALID_PHONE_NUMBER -> R.string.InvalidPhoneNumber
        RegistrationOrSignInError.DUPLICATE_USERNAME -> R.string.DuplicateUsername
        RegistrationOrSignInError.DEVICE_ALREADY_REGISTERED -> R.string.DeviceAlreadyRegistered
        RegistrationOrSignInError.TOKEN_NOT_FOUND -> R.string.TokenNotFound
        RegistrationOrSignInError.CLOUD_AUTHENTICATION_FAILURE -> R.string.CloudAuthenticationFailure
        RegistrationOrSignInError.CLOUD_STORAGE_FAILURE -> R.string.CloudStorageFailure
        RegistrationOrSignInError.USER_ALREADY_SIGNED_IN -> R.string.UserAlreadySignedIn
        RegistrationOrSignInError.INVALID_CREDENTIALS -> R.string.InvalidCredentials
        RegistrationOrSignInError.INVALID_USERNAME_FORMAT -> R.string.InvalidUsername
        RegistrationOrSignInError.USERNAME_TOO_SHORT -> R.string.UsernameTooShort
        RegistrationOrSignInError.PASSWORD_TOO_SHORT -> R.string.PasswordTooShort
        RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS -> R.string.PasswordWithoutUppercaseNumberSpecialCharacters
        RegistrationOrSignInError.INVALID_EMAIL_FORMAT -> R.string.InvalidEmailFormat
        RegistrationOrSignInError.INVALID_VERIFICATION_CODE -> R.string.InvalidVerificationCode
        RegistrationOrSignInError.VERIFICATION_CODE_NOT_SENT -> R.string.VerificationCodeNotSent
    }

    return context.getString(resId)
}