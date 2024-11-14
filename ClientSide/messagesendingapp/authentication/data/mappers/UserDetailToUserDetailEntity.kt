package com.example.messagesendingapp.authentication.data.mappers

import com.example.messagesendingapp.authentication.data.Local.entities.UserDetailEntity
import com.example.messagesendingapp.core.domain.util.UserDetails

fun UserDetails.toUserDetailEntity(): UserDetailEntity{
    return UserDetailEntity(
        userName = this.userName,
        phoneNumber = this.phoneNumber,
        email = this.email,
        password = this.password,
        userToken = this.userToken
    )
}