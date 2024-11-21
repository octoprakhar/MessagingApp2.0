package com.example.messagesendingapp.user_conversations.presentation

import com.example.messagesendingapp.user_conversations.presentation.models.ConversationUiModel

//Every value that can change the conversation screen state
data class ConversationScreenState(
    val conversations: List<ConversationUiModel> = (1..100).map { ConversationUiModel().copy(id = it.toString()) },
    val selectedConversations: List<ConversationUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isMessageSelected: Boolean = false,
    val isProfilePhotoEnlarged: Boolean = false,
    val selectedProfilePhotoId : String? = null,
    val isEnlargedImageClicked: Boolean = false,
    val isMoreOptionsVisible: Boolean = false,
    val isUserSearching: Boolean = false,
    val isPinnedClicked : Boolean = false,
    val noOfSelectedConversations: Int = 0,
    val isDeleteClicked : Boolean = false,
    val isArchiveClicked : Boolean = false,
    val isNotificationBlockClicked : Boolean = false,
    val isMarkAsReadClicked : Boolean = false,
    val isSelectAllClicked : Boolean = false,
    val isAddToFavoriteClicked : Boolean = false,
    val isBlockClicked : Boolean = false,
    val isSearchStateDialogBoxDismissed: Boolean = true,
    val bottomBarButtonClicked : Int = 0,
    val searchText : String = "",

    //state of search top bar
    val indexOfTagSelected: Int = -1
    )

// label indexes from 0 to 6 = "Unread","Photos","Videos","Links","GIFs","Audio","Documents"
