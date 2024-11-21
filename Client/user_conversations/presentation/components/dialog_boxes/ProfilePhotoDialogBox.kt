package com.example.messagesendingapp.user_conversations.presentation.components.dialog_boxes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.models.ConversationUiModel

@Composable
fun PhotoDialogBox(
    modifier: Modifier = Modifier,
    conversation: ConversationUiModel,
    actions: (ConversationActions) -> Unit
){
    Dialog(
        onDismissRequest = { actions(ConversationActions.onPhotoAlertBoxDismissed) }
    ) {
        Surface( // Use a Surface to define the dialog's shape and size
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(16.dp)),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp) // Add padding to make the content visually clear
                    .clickable {
                        // Navigate to enlarged image page
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(300.dp) // Explicitly size the Icon
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            //TODO navigate to enlarged image page
                        },
                    imageVector = ImageVector.vectorResource(id = conversation.profilePhoto.toInt()),
                    contentDescription = "Profile photo"
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp).clickable {
                            //TODO show message page
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.chat_icon),
                        contentDescription = "Chat"
                    )
                    Icon(
                        modifier = Modifier.size(32.dp).clickable {
                            //TODO call the user or open the calling page
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_call_24),
                        contentDescription = "Call"
                    )
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                //TODO open the video call page
                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.video_icon),
                        contentDescription = "Video Call"
                    )
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                //TODO open the user description page
                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.info_image),
                        contentDescription = "More Info"
                    )
                }
            }
        }
    }



}