package com.example.messagesendingapp.user_conversations.presentation.models

//How a conversation in Ui looks like
data class ConversationUiModel(
    val id: String = "",
    val friendName: String = "John Dallock",
    val profilePhoto: String = "2131230859",
    val lastMessage: String = "there will be the last message of conversation",
    val timeOfLastMessage: String = "yesterday",
    val noOfUnreadMessages: Int = 1,
    val isLongClickedPressed : Boolean = false,
    var isPinned : Boolean = false,
    val isArchived : Boolean = false,
    val isBlocked : Boolean = false,
    val isAddedToFavorites : Boolean = false,
    val isNotificationBlocked : Boolean = false,
    val isStarred : Boolean = false,
    val isMarkedUnread : Boolean = false,

    )
