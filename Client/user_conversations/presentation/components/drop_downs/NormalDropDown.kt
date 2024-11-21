package com.example.messagesendingapp.user_conversations.presentation.components.drop_downs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState

@Composable
fun NormalDropDown(
    modifier: Modifier = Modifier,
    backgroundColor : Color = Color.White,
    state: ConversationScreenState,
    actions: (ConversationActions) -> Unit
) {
    DropdownMenu(
        expanded = state.isMoreOptionsVisible && state.noOfSelectedConversations == 0,
        onDismissRequest = { actions(ConversationActions.onMoreDisabeledClicked) },
        modifier = modifier.background(backgroundColor)
    ) {
        DropdownMenuItem(
            text = { Text(text = "New group", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
                actions(ConversationActions.onNewGroupClicked)
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        DropdownMenuItem(
            text = { Text(text = "New broadcast", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
                actions(ConversationActions.onNewBroadcastClicked)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Starred messages", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
                actions(ConversationActions.onStarredMessagesClicked)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Settings", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
                actions(ConversationActions.onSettingsClicked)
            }
        )
    }
}
