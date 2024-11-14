package com.example.messagesendingapp.authentication.data.mappers

import com.example.messagesendingapp.authentication.data.Networking.dto.SignInDto
import com.example.messagesendingapp.core.domain.util.UserDetails

fun UserDetails.toSignInDto() : SignInDto{
    return SignInDto(
        email = this.email,
        password = this.password
    )
}