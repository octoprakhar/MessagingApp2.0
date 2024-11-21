package com.example.messagesendingapp.user_conversations.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConversationEntity(
    @PrimaryKey(autoGenerate = false)
    val conversationId: String,
    val friendId : String,
    val friendName : String,
    val friendPhoneNumber : String,
    val friendEmail : String,
    val friendProfilePhotoLink : String? = null, //I don't want to store photo of other person locally
    val conversationNotificationChannel : String,
    val lastTimeConversationVisited: Long = 0L,//0 because maybe conversation has never started or maybe message deleted or so on
    val isConversationPinned : Boolean = false,
    val isConversationNotificationBlocked : Boolean = false,
    val isConversationArchived : Boolean = false,
    val isConversationFavorite: Boolean = false,
    val isConversationBlocked : Boolean = false
)
