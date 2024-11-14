package com.example.messagesendingapp.authentication.data.Local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val userToken: String
)
