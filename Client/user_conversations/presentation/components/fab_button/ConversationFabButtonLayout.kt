package com.example.messagesendingapp.user_conversations.presentation.components.fab_button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions

@Composable
fun ConversationFabButtonLayout(
    modifier: Modifier = Modifier,
    backgroundColor : Color,
    buttonIcon: Int,
    actions: (ConversationActions) -> Unit,
    iconColor: Color = Color.White
){
    Box (
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(backgroundColor,RoundedCornerShape(32.dp))
            .clickable { actions(ConversationActions.onFABClicked) }
        ,
        contentAlignment = Alignment.Center
    ){
            Icon(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                imageVector = ImageVector.vectorResource(id = buttonIcon),
                contentDescription = "Contacts",
                tint = iconColor
            )
    }
}