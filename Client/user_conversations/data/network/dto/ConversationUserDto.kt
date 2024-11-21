package com.example.messagesendingapp.user_conversations.data.network.dto

//What information we want to get from cloud and send to cloud as a user
data class ConversationUserDto(
    val friendName : String,
    val friendPhoneNumber : String,
    val friendEmail : String,
    val friendProfilePhotoLink : String? = null,
)
