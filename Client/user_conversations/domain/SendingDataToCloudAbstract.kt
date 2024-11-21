package com.example.messagesendingapp.user_conversations.domain

import com.example.messagesendingapp.core.domain.errors.NetworkError
import com.example.messagesendingapp.core.domain.utils.Result

interface SendingDataToCloudAbstract {

    //Data regarding blocking feature
    suspend fun onBlockingStatusChange(conversationId: String) : Result<Unit, NetworkError>

    //Data regarding whether or not user is connected to internet
    suspend fun onInternetConnectionStatusChange() : Result<Boolean, NetworkError>

}