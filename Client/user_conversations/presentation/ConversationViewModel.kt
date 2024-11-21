package com.example.messagesendingapp.user_conversations.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConversationViewModel : ViewModel() {

    private val TAG = "ConversationViewModel"

    // Managing state of the screen
    private val _state = MutableStateFlow(ConversationScreenState())
    val state = _state.asStateFlow()

    // When actions taken
    fun onAction(actions: ConversationActions) {
        when (actions) {
            is ConversationActions.conversationLongPressed -> {
                // Update all conversations
                val updatedConversations = _state.value.conversations.map { conversation ->
                    if (conversation.id == actions.conversationId) {
                        conversation.copy(isLongClickedPressed = true)
                    } else {
                        conversation
                    }
                }

                // Get the new long-pressed conversation
                val longPressedConversation =
                    updatedConversations.find { it.id == actions.conversationId }

                if (longPressedConversation != null) {
                    // Create a new list with the existing selected conversations + the new one
                    val updatedSelectedConversations =
                        _state.value.selectedConversations.toMutableList()
                    if (!updatedSelectedConversations.contains(longPressedConversation)) {
                        updatedSelectedConversations.add(longPressedConversation)
                    }

                    // Update the state with the new conversations and selected conversations
                    if (_state.value.isUserSearching){
                        _state.value = _state.value.copy(
                            conversations = updatedConversations,
                            selectedConversations = updatedSelectedConversations,
                            noOfSelectedConversations = updatedSelectedConversations.size,
                            isSearchStateDialogBoxDismissed = false
                        )
                    }else{
                        _state.value = _state.value.copy(
                            conversations = updatedConversations,
                            selectedConversations = updatedSelectedConversations,
                            noOfSelectedConversations = updatedSelectedConversations.size
                        )
                    }

                }
                Log.d(TAG, "onAction from pure long press: ${_state.value.selectedConversations}")
                Log.d(
                    TAG,
                    "onAction from pure long press: ${_state.value.selectedConversations.size}"
                )

            }

            is ConversationActions.conversationShortPressed -> {
                // Handle short press logic
            }

            is ConversationActions.conversationShortPressedClickedEarlierALongPressed -> {
                // Update all conversations
                val updatedConversations = _state.value.conversations.map { conversation ->
                    if (conversation.id == actions.conversationId) {
                        conversation.copy(isLongClickedPressed = false)
                    } else {
                        conversation
                    }
                }

                // Identify the conversation that was short-pressed
                val shortPressedConversation =
                    _state.value.selectedConversations.find { it.id == actions.conversationId }

                if (shortPressedConversation != null) {
                    // Remove the short-pressed conversation from the selected list
                    val updatedSelectedConversations =
                        _state.value.selectedConversations.filter { it.id != actions.conversationId }

                    // Update the state
                    _state.value = _state.value.copy(
                        conversations = updatedConversations,
                        selectedConversations = updatedSelectedConversations,
                        noOfSelectedConversations = updatedSelectedConversations.size
                    )

                    Log.d(
                        TAG,
                        "Updated Selected Conversations: ${_state.value.selectedConversations}"
                    )
                    Log.d(
                        TAG,
                        "Number of Selected Conversations: ${_state.value.noOfSelectedConversations}"
                    )
                } else {
                    Log.d(TAG, "Conversation not found in selected list")
                }
            }
            is ConversationActions.onProfilePhotoClicked -> {
                //Show image in enlarged form in alert dialog box
                _state.value = _state.value.copy(
                    isProfilePhotoEnlarged = true,
                    selectedProfilePhotoId = actions.conversationId
                )
            }

            ConversationActions.onPhotoAlertBoxDismissed -> {
                //dismiss the alert dialog box and change the state
                _state.value = _state.value.copy(
                    isProfilePhotoEnlarged = false
                )
            }



            is ConversationActions.onBottomBarButtonClicked -> {
                //Tell the given button id to state
                _state.value = _state.value.copy(
                    bottomBarButtonClicked = actions.buttonId
                )
            }

            ConversationActions.onFABClicked -> {
                //Navigate to contact screen
            }

            ConversationActions.onCameraClicked -> {
                //Trigger navigation even to navigate to camera screen
            }
            ConversationActions.onMoreClicked -> {
                //Check which drop down menu to give
                //and make isMoreOptionsVisible true
                _state.value = _state.value.copy(
                    isMoreOptionsVisible = true,

                )
            }

            ConversationActions.onMoreDisabeledClicked -> {
                //just set isMoreOptionsVisible to false
                _state.value = _state.value.copy(
                    isMoreOptionsVisible = false
                )
            }

            ConversationActions.onNewBroadcastClicked -> {
                //Send to selected screen from contacts so that user can select persons to broadcast
            }
            ConversationActions.onNewGroupClicked -> {
                //Send to selected screen from contacts so that user can select persons to make new group
            }
            ConversationActions.onSettingsClicked -> {
                //Navigate to settings screen
            }
            ConversationActions.onStarredMessagesClicked -> {
                //Navigate to starred messages screen
            }

            ConversationActions.onAddToFavouritesClicked -> TODO()
            ConversationActions.onAddToListiClicked -> TODO()
            ConversationActions.onBlockClicked -> TODO()
            ConversationActions.onLockChatClicked -> TODO()
            ConversationActions.onMarkAsUnreadClicked -> TODO()
            ConversationActions.onSeletAllClicked -> TODO()
            ConversationActions.onViewContactClicked -> TODO()
            ConversationActions.onArchiveClicked -> TODO()
            ConversationActions.onDeleteClicked -> TODO()

            ConversationActions.onNotificationClicked -> {
                //Switch on the notification for each selected message
            }
            ConversationActions.onUnpinClicked -> {
                //Set isPinned to false for each selected message
                _state.value = _state.value.copy(
                    conversations = _state.value.conversations.map {
                        if (_state.value.selectedConversations.contains(it)){
                            it.copy(isPinned = false)
                        }else{
                            it
                        }
                    },
                    selectedConversations = emptyList(),
                    noOfSelectedConversations = 0
                )
            }
            ConversationActions.onNotificationOffClicked -> {
                //Switch off the notification for each selected message

            }
            ConversationActions.onPinClicked -> {
                //Set isPinned to true for each selected message
                _state.value = _state.value.copy(
                    conversations = _state.value.conversations.map {
                        if (_state.value.selectedConversations.contains(it)){
                            it.copy(isPinned = true)
                        }else{
                            it
                        }
                    },
                    selectedConversations = emptyList(),
                    noOfSelectedConversations = 0
                )
            }

            ConversationActions.onBackButtonClicked -> {
                //Make selected conversation as empty list and no. of conversations to 0
                _state.value = _state.value.copy(
                    selectedConversations = emptyList(),
                    noOfSelectedConversations = 0
                )
            }

            is ConversationActions.onSearchTextChanges -> {
                //Change searchText value of the state
                _state.value = _state.value.copy(
                    searchText = actions.searchText
                )
            }

            ConversationActions.onSearchBoxClicked -> {
                //change is search visible to true
                _state.value = _state.value.copy(
                    isUserSearching = true
                )
            }

            ConversationActions.onSearchBackButtonPressed -> {
                //change is search visible to false
                _state.value = _state.value.copy(
                    isUserSearching = false,
                    searchText = ""
                )
            }


            is ConversationActions.onUncheckSearchTag -> {
                _state.value = _state.value.copy(
                    indexOfTagSelected = -1
                )
            }

            is ConversationActions.onSearchTagWithIndexClicked -> {
                _state.value = _state.value.copy(
                    indexOfTagSelected = actions.searchTagIndex
                )
            }

            ConversationActions.onSearchStateDialogBoxDismissed -> {
                _state.value = _state.value.copy(
                    isSearchStateDialogBoxDismissed = true
                )
            }
        }
    }

    //Tell whether to show pinned image or unpinned image
    /*
    When "iconRef" is
    0 -> Tell about pinning status
    1 -> Tell about notification blocking status
    2 -> Conversation blocking status
     */
    fun showIconStatus(iconRef: Int) : Int{
        return when(iconRef){
            0 -> {if(_state.value.selectedConversations.all { it.isPinned }) 0 else 1}
            1 -> {if(_state.value.selectedConversations.all { it.isNotificationBlocked }) 0 else 1}
            else -> {if(_state.value.selectedConversations.all { it.isBlocked }) 0 else 1}
        }
    }
}






