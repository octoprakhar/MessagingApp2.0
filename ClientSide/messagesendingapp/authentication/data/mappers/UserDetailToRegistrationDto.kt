package com.example.messagesendingapp.authentication.data.mappers

import com.example.messagesendingapp.authentication.data.Networking.dto.RegistrationDto
import com.example.messagesendingapp.core.domain.util.UserDetails

fun UserDetails.toRegistrationDto(): RegistrationDto {
    return RegistrationDto(
        userName = this.userName,
        phoneNumber = this.phoneNumber,
        email = this.email,
        password = this.password,
        userToken = this.userToken
    )

}