package com.example.messagesendingapp.user_conversations.presentation.components.dialog_boxes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState

@Composable
fun SearchingStateDialogBox(
    modifier: Modifier = Modifier,
    state: ConversationScreenState,
    actions: (ConversationActions) -> Unit
){
    Log.d("SearchState","Dialog box called")
    Dialog(
        onDismissRequest = { actions(ConversationActions.onSearchStateDialogBoxDismissed) }
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

            ) {
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )
                Text(
                    text = "View contact",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 64.dp)
                        .clickable {
                            //TODO navigate to user description page
                        },
                    fontSize = 32.sp
                )


            }
        }
    }

}