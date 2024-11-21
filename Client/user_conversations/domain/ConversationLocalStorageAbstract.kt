package com.example.messagesendingapp.user_conversations.domain

import com.example.messagesendingapp.core.domain.Conversation
import com.example.messagesendingapp.core.domain.errors.ConversationError
import com.example.messagesendingapp.core.domain.utils.Result

interface ConversationLocalStorageAbstract {

    //Inserting a new conversation
    suspend fun insertNewConversation(conversation: Conversation) : Result<String, ConversationError>

    //Deleting a conversation
    suspend fun deleteConversation(conversationId: String) : Result<Unit, ConversationError>

    //Updating last visited time of a conversation
    suspend fun updateLastVisitedTimeOfConversation(conversationId: String) : Result<Unit, ConversationError>

    // Getting a conversation
    suspend fun getConversation(conversationId: String) : Result<Conversation, ConversationError>

    //Getting all conversations
    suspend fun getAllConversations() : Result<List<Conversation>, ConversationError>

    //Blocking/Unblocking a conversation need to send this detail in cloud too
    suspend fun blockConversationFeature(conversationId: String) : Result<Unit, ConversationError>

    //Archiving/Unarchiving a conversation
    suspend fun archiveConversationFeature(conversationId: String) : Result<Unit, ConversationError>

    //Pinning/Unpinning a conversation
    suspend fun pinConversationFeature(conversationId: String) : Result<Unit, ConversationError>

    //Favorite/Unfavorite a conversation
    suspend fun favoriteConversationFeature(conversationId: String) : Result<Unit, ConversationError>

    //Notification blocking/unblocking a conversation
    suspend fun notificationBlockConversationFeature(conversationId: String) : Result<Unit, ConversationError>
}