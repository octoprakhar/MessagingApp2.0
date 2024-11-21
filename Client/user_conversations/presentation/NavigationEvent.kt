package com.example.messagesendingapp.user_conversations.presentation

sealed interface NavigationEvent {
    data object toContactScreen : NavigationEvent
    data object toUpdatesScreen: NavigationEvent
    data object toCameraScreen: NavigationEvent
    data object toCallsScreen: NavigationEvent
    data object toGroupsScreen: NavigationEvent
}