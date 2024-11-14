package com.example.messagesendingapp.authentication.data.util

import com.example.messagesendingapp.authentication.data.Networking.dto.RegistrationDto
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError

// Normal checks for registration before sending to cloud
fun RegistrationCredentialsChecker(registrationDto: RegistrationDto) : Result<Unit, RegistrationOrSignInError>{

    val emailRegEx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    // Checks for user name
    if (registrationDto.userName.isEmpty()){
        return Result.Error(RegistrationOrSignInError.INVALID_USERNAME_FORMAT)
    }else if (registrationDto.userName.length <= 3){
        return Result.Error(RegistrationOrSignInError.INVALID_USERNAME_FORMAT)
    }

    // Checks for password
    if (registrationDto.password.isEmpty()){
        return Result.Error(RegistrationOrSignInError.PASSWORD_TOO_SHORT)
    }else if (registrationDto.password.length <= 7){
        return Result.Error(RegistrationOrSignInError.PASSWORD_TOO_SHORT)
    }else if(!registrationDto.password.any{it.isUpperCase()}){
        return Result.Error(RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS)
    }else if(!registrationDto.password.any{it.isDigit()}){
        return Result.Error(RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS)
    }else if(!registrationDto.password.any{it.isLetter()}){
        return Result.Error(RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS)
    }else if(!registrationDto.password.any{!it.isLetterOrDigit()}){
        return Result.Error(RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS)

    }

    // Check for email
    if (!registrationDto.email.matches(emailRegEx)){
        return Result.Error(RegistrationOrSignInError.INVALID_EMAIL_ADDRESS)
    }

    //Check for phone number
    if (registrationDto.phoneNumber.any{!it.isDigit()}){
        return Result.Error(RegistrationOrSignInError.INVALID_PHONE_NUMBER)
    }else if (registrationDto.phoneNumber.length != 10){
        return Result.Error(RegistrationOrSignInError.INVALID_PHONE_NUMBER)
    }

    //Check for user token
    if (registrationDto.userToken.isEmpty() || registrationDto.userToken.isBlank()){
        return Result.Error(RegistrationOrSignInError.TOKEN_NOT_FOUND)
    }

    return Result.Success(Unit)


}