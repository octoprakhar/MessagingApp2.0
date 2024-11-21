package com.example.messagesendingapp.user_conversations.presentation.components.top_bar.long_pressed_top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MessageSendingAppTheme
import com.example.compose.onTertiaryContainerDark
import com.example.compose.outlineVariantDarkHighContrast
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.tertiaryDarkMediumContrast
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState
import com.example.messagesendingapp.user_conversations.presentation.components.drop_downs.LongPressedDropDown
import com.example.messagesendingapp.user_conversations.presentation.components.drop_downs.NormalDropDown

//This top bar will appear when we do long press in the conversation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongPressedTopBar(
    modifier: Modifier = Modifier,
    state: ConversationScreenState,
    actions: (ConversationActions) -> Unit,
    showIconsStatus: (Int) -> Int // Check viewmodel for this method
) {
    //Matching the theme
    val topBarBackgroundColor = if(isSystemInDarkTheme()){
        onTertiaryContainerDark

    }else{
        tertiaryDarkMediumContrast
    }

    //DropDown color
    val dropDownColor = if (isSystemInDarkTheme()){
        primaryContainerDarkMediumContrast
    }else{
        outlineVariantDarkHighContrast
    }



    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarBackgroundColor,
            scrolledContainerColor = topBarBackgroundColor
        ),
        modifier = modifier,
        title = { Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "${state.noOfSelectedConversations}",
            fontWeight = FontWeight.Bold
        )
                },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        actions(ConversationActions.onBackButtonClicked)
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.nav_back_image),
                contentDescription = "back"
            )
        },
        actions = {
            if (showIconsStatus(0) == 0){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.unpining_image),
                    contentDescription = "unpin",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            actions(ConversationActions.onUnpinClicked)

                        }
                )
            }else{
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.pin_image),
                    contentDescription = "pin",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            actions(ConversationActions.onPinClicked)
                        }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))


            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.delete_image),
                contentDescription ="delete" ,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        actions(ConversationActions.onDeleteClicked)
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))


            if (showIconsStatus(1) == 0){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.notification_on_image),
                    contentDescription = "notification on",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            actions(ConversationActions.onNotificationClicked)
                        }
                )
            }else{
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.notification_off_image),
                    contentDescription = "notification off",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            actions(ConversationActions.onNotificationOffClicked)
                        }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.archive_image),
                contentDescription = "archive",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        actions(ConversationActions.onArchiveClicked)
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))


            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        actions(ConversationActions.onMoreClicked)
                    }
            )

            //Checking state whether more button is clicked or not And showing drop down
            if(state.isMoreOptionsVisible && state.selectedConversations.isNotEmpty()){
                Box (
                    modifier = Modifier.offset(y = 20.dp) // Offset to push it slightly below
//                        .background(topBarBackgroundColor)

                ){
                    LongPressedDropDown(state = state, actions = actions, backgroundColor = dropDownColor)
                }
            }
        }
    )
    
}

@Preview
@Composable
fun LongPressedTopBarPreview(){
    MessageSendingAppTheme {
        LongPressedTopBar(state = ConversationScreenState(), actions = {}, showIconsStatus = {1})
    }
}