package com.example.messagesendingapp.user_conversations.presentation.components.drop_downs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun LongPressedDropDown(
    modifier: Modifier = Modifier,
    backgroundColor : Color = Color.White,
    state: ConversationScreenState,
    actions: (ConversationActions) -> Unit
) {
    DropdownMenu(
        expanded = state.isMoreOptionsVisible && state.noOfSelectedConversations != 0,
        onDismissRequest = { actions(ConversationActions.onMoreDisabeledClicked) },
        modifier = modifier.background(backgroundColor)
    ) {
        DropdownMenuItem(
            text = { Text(text = "View Contact", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        DropdownMenuItem(
            text = { Text(text = "Mark as unread", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Select all", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Lock chat", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Add to Favorites", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownMenuItem(
            text = { Text(text = "Add to list", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
        DropdownMenuItem(
            text = { Text(text = "Block", fontSize = 20.sp) },
            onClick = {
                // Handle menu item click
            }
        )
    }
}