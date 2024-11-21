package com.example.messagesendingapp.user_conversations.presentation

//All actions except navigation that can be done in conversation screen
sealed interface ConversationActions {

    //Clicking action on conversation
    data class conversationLongPressed(val conversationId: String) : ConversationActions
    data class conversationShortPressed(val conversationId: String) : ConversationActions
    data class conversationShortPressedClickedEarlierALongPressed(val conversationId: String) : ConversationActions
    data class onProfilePhotoClicked(val conversationId: String) : ConversationActions
    data object onPhotoAlertBoxDismissed: ConversationActions

    //Actions for bottom bar buttons
    data class onBottomBarButtonClicked(val buttonId: Int) : ConversationActions

    //For FAB
    data object onFABClicked : ConversationActions

    //For Camera navigation
    data object onCameraClicked : ConversationActions

    //for more options clicked
    data object onMoreClicked : ConversationActions
    data object onMoreDisabeledClicked : ConversationActions

    //Normal drop down menu items actions
    data object onNewGroupClicked : ConversationActions
    data object onNewBroadcastClicked : ConversationActions
    data object onStarredMessagesClicked : ConversationActions
    data object onSettingsClicked : ConversationActions

    //Long pressed top bar actions
    data object onUnpinClicked : ConversationActions
    data object onDeleteClicked : ConversationActions
    data object onNotificationClicked : ConversationActions
    data object onArchiveClicked : ConversationActions
    data object onPinClicked : ConversationActions
    data object onNotificationOffClicked : ConversationActions
    data object onBackButtonClicked: ConversationActions

    //Long pressed drop down menu items actions
    data object onViewContactClicked : ConversationActions
    data object onMarkAsUnreadClicked : ConversationActions
    data object onBlockClicked : ConversationActions
    data object onSeletAllClicked : ConversationActions
    data object onLockChatClicked : ConversationActions
    data object onAddToFavouritesClicked : ConversationActions
    data object onAddToListiClicked : ConversationActions

    //search field action
    data object onSearchBoxClicked : ConversationActions
    data class onSearchTextChanges(val searchText : String) : ConversationActions

    //Search Top bar actions
    data object onSearchBackButtonPressed : ConversationActions
    data object onUncheckSearchTag: ConversationActions
    data class onSearchTagWithIndexClicked(val searchTagIndex: Int): ConversationActions
    data object onSearchStateDialogBoxDismissed : ConversationActions




}