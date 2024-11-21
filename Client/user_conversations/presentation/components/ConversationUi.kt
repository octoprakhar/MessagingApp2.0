package com.example.messagesendingapp.user_conversations.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.MessageSendingAppTheme
import com.example.compose.secondaryContainerLight
import com.example.compose.secondaryContainerLightHighContrast
import com.example.compose.tertiaryContainerLight
import com.example.messagesendingapp.R
import com.example.messagesendingapp.core.presentation.util.CutString
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenStyle
import com.example.messagesendingapp.user_conversations.presentation.components.dialog_boxes.SearchingStateDialogBox
import com.example.messagesendingapp.user_conversations.presentation.models.ConversationUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConversationUi(
    modifier: Modifier = Modifier,
    style: ConversationScreenStyle,
    conversationUiModel: ConversationUiModel,
    actions: (ConversationActions) -> Unit,
    state: ConversationScreenState

) {

    //Matching the theme
    val conversationBackgroundColor = if(isSystemInDarkTheme()){
        secondaryContainerLightHighContrast

    }else{
        secondaryContainerLight
    }

    val unreadMessageColor = if (isSystemInDarkTheme()){
        tertiaryContainerLight

    }else{
        Color(0xFF1da341)
    }

    val contentColor = if (isSystemInDarkTheme()){
        Color.White
    }else{
        Color.Black
    }

    val longPressedColor = if (isSystemInDarkTheme()) secondaryContainerLight else secondaryContainerLightHighContrast

    //Variable for long press and short press
    val interactionSource = remember {
        MutableInteractionSource()
    }

    //Managing state when long press is done
    var isLongPressed by remember {
        mutableStateOf(false)
    }
    var isPinned by remember {
        mutableStateOf(conversationUiModel.isPinned)
    }

    //Whenever selected conversation is changed always check whether it contains our conversation or not
    //Because if not then set isLongPressed to false
    LaunchedEffect(state.selectedConversations) {
        if (!state.selectedConversations.contains(conversationUiModel)){
            isLongPressed = false
        }
    }

    //Also check for pinned message
    LaunchedEffect(conversationUiModel.isPinned) {
        if (!conversationUiModel.isPinned){
            isPinned = false
        }else{
            isPinned = true
        }
    }

    //If user searching is on
    if (state.isUserSearching && state.isSearchStateDialogBoxDismissed == false) {
        SearchingStateDialogBox(state = state, actions = actions)

    }


    //start making the layout
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(style.heightOfAConversationDp)
            .padding(style.conversationPaddingVerticalDp)
            .background(if (!isLongPressed) conversationBackgroundColor else longPressedColor)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = {
                    // Handle click

                    /*Check if conversation's ui long press is true then set it to false
                     */
                    if (conversationUiModel.isLongClickedPressed) {
                        isLongPressed = false
                        actions(
                            ConversationActions.conversationShortPressedClickedEarlierALongPressed(
                                conversationUiModel.id
                            )
                        )
                    } else if (state.noOfSelectedConversations > 0) {
                        //Then act as a long press only
                        isLongPressed = true
                        actions(ConversationActions.conversationLongPressed(conversationUiModel.id))
                    } else {

                        actions(ConversationActions.conversationShortPressed(conversationUiModel.id))
                    }
                },
                onLongClick = {
                    // Handle long click


                    //Set true to change the color
                    isLongPressed = true

                    //Perform some actions
                    actions(ConversationActions.conversationLongPressed(conversationUiModel.id))


                }
            )
    ) {
        //Image area
        Box(
            modifier = Modifier
                .height(style.profileImageAreaHeightDp)
                .width(style.profileImageAreaWidthDp)
                .padding(
                    vertical = style.profileImageAreaPaddingVerticalDp,
                    horizontal = style.profileImageAreaPaddingHorizontalDp
                )
                .clip(CircleShape) // Apply clipping after padding and before the border
                .border(1.dp, Color.Black, CircleShape)
                .clickable {
                    actions(ConversationActions.onProfilePhotoClicked(conversationUiModel.id))
                }
        ) {
            // Content inside the Box
            Icon(
                imageVector = ImageVector.vectorResource(id = conversationUiModel.profilePhoto.toInt()),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        //Content area
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = style.contentAreaPaddingHorizontalDp,
                    vertical = style.contentAreaPaddingVerticalDp
                )
        ) {

            //Name of the friend
            Text(
                text = conversationUiModel.friendName,
                modifier = Modifier
                    .padding(
                        horizontal = style.friendsNamePaddingHorizontalDp,
                        vertical = style.friendsNamePaddingVerticalDp
                    ),
                fontSize = style.friendsNameSizeSp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            //Last message
            Text(
                text = if (!isPinned) conversationUiModel.lastMessage.CutString(80) else conversationUiModel.lastMessage.CutString(20),
                modifier = Modifier
                    .padding(
                        horizontal = style.lastMessagePaddingHorizontalDp,
                        vertical = style.lastMessagePaddingVerticalDp
                    ),
                fontSize = style.lastMessageSizeSp,
                fontWeight = FontWeight.Medium,
                color = contentColor
            )
        }

        //Last message time and unread message count, pinned or unpin
        Row (
            verticalAlignment = Alignment.Top
        ){
            if (conversationUiModel.isPinned){
                Icon(
                    modifier = Modifier
                        .size(21.dp)
                        .offset(y = 21.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_push_pin_24),
                    contentDescription = "pinned",
                    tint = Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        horizontal = style.extraAreaPaddingHorizontalDp,
                        vertical = style.extraAreaPaddingVerticalDp
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Last message time
                Text(
                    text = conversationUiModel.timeOfLastMessage,
                    modifier = Modifier
                        .padding(
                            horizontal = style.lastMessageTimePaddingHorizontalDp,
                            vertical = style.lastMessageTimePaddingVerticalDp
                        ),
                    fontSize = style.lastMessageTimeSizeSp,
                    fontWeight = FontWeight.Light,
                    color = if (conversationUiModel.noOfUnreadMessages > 0) unreadMessageColor else contentColor
                )

                //Unread message count

                if (conversationUiModel.noOfUnreadMessages > 0) {
                    Box(
                        modifier = Modifier
                            .padding(
                                horizontal = style.unreadMessageCountPaddingHorizontalDp,
                                vertical = style.unreadMessageCountPaddingVerticalDp
                            )
                            .size(style.unreadMessageCountSizeDp) // Assign both height and width
                            .clip(CircleShape)
                            .background(unreadMessageColor), // Add a background to make it stand out
                        contentAlignment = Alignment.Center // Center the text
                    ) {
                        Text(
                            text = conversationUiModel.noOfUnreadMessages.toString(),
                            fontSize = 13.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }
        }


    }

}

@Preview
@Composable
fun ConversationUiPreview() {
    MessageSendingAppTheme {

        ConversationUi(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            style = style,
            conversationUiModel = ConversationUiModel(),
            actions = {},
            state = ConversationScreenState()
        )
    }
}

internal val style = ConversationScreenStyle(
    heightOfAConversationDp = 100.dp,
    conversationPaddingVerticalDp = 0.dp,
    totalContentPaddingVerticalDp = 8.dp,
    totalContentPaddingHorizontalDp = 8.dp,
    profileImageAreaWidthDp = 80.dp,
    profileImageAreaHeightDp = 100.dp,
    profileImageAreaPaddingVerticalDp = 8.dp,
    profileImageAreaPaddingHorizontalDp = 8.dp,
    contentAreaPaddingVerticalDp = 8.dp,
    contentAreaPaddingHorizontalDp = 8.dp,
    extraAreaPaddingVerticalDp = 8.dp,
    extraAreaPaddingHorizontalDp = 4.dp,
    friendsNameSizeSp = 15.sp,
    friendsNamePaddingVerticalDp = 8.dp,
    friendsNamePaddingHorizontalDp = 4.dp,
    lastMessageSizeSp = 12.sp,
    lastMessagePaddingVerticalDp = 4.dp,
    lastMessagePaddingHorizontalDp = 4.dp,
    lastMessageTimeSizeSp = 12.sp,
    lastMessageTimePaddingVerticalDp = 2.dp,
    lastMessageTimePaddingHorizontalDp = 2.dp,
    unreadMessageCountSizeDp = 27.dp,
    unreadMessageCountPaddingVerticalDp = 8.dp,
    unreadMessageCountPaddingHorizontalDp = 2.dp,
)