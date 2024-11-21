package com.example.messagesendingapp.user_conversations.data.network.dto

data class SendingInfoRegardingConversationDto(
    val yourName : String,
    val friendName: String,
    val isConversationBlocked : Boolean,
    val areYouConnectedToInternet: Boolean
)
