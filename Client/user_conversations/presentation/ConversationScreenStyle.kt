package com.example.messagesendingapp.user_conversations.presentation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

//Values related to conversation screen styling
/*
Nomenclature: For a single conversation(Imagine main page of a whatsapp app) ->
1. heightOfAConversationDp = It means the total height a single conversation takes which will be in dp.
2. Profile image area -> The total left side area which will be taken by profile photo
3. Content area -> It will have two things , name of the friend and last message sent/receive.
4. Extra area -> it will have two things, last message sent time and unread message count.
5. Total Content -> That means I am talking about content of(profile area + content area + extra area)

 */
data class ConversationScreenStyle(
    val heightOfAConversationDp : Dp,
    val conversationPaddingVerticalDp: Dp,
    val totalContentPaddingVerticalDp: Dp,
    val totalContentPaddingHorizontalDp: Dp,
    val profileImageAreaWidthDp: Dp,
    val profileImageAreaHeightDp: Dp,
    val profileImageAreaPaddingVerticalDp: Dp,
    val profileImageAreaPaddingHorizontalDp: Dp,
    val contentAreaPaddingVerticalDp: Dp,
    val contentAreaPaddingHorizontalDp: Dp,
    val extraAreaPaddingVerticalDp: Dp,
    val extraAreaPaddingHorizontalDp: Dp,
    val friendsNameSizeSp: TextUnit,
    val friendsNamePaddingVerticalDp: Dp,
    val friendsNamePaddingHorizontalDp: Dp,
    val lastMessageSizeSp: TextUnit,
    val lastMessagePaddingVerticalDp: Dp,
    val lastMessagePaddingHorizontalDp: Dp,
    val lastMessageTimeSizeSp: TextUnit,
    val lastMessageTimePaddingVerticalDp: Dp,
    val lastMessageTimePaddingHorizontalDp: Dp,
    val unreadMessageCountSizeDp: Dp,
    val unreadMessageCountPaddingVerticalDp: Dp,
    val unreadMessageCountPaddingHorizontalDp: Dp,

)
