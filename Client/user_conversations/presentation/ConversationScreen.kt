package com.example.messagesendingapp.user_conversations.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MessageSendingAppTheme
import com.example.compose.inversePrimaryDarkHighContrast
import com.example.compose.outlineVariantDarkMediumContrast
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.secondaryContainerLight
import com.example.compose.secondaryContainerLightHighContrast
import com.example.compose.surfaceBrightDarkMediumContrast
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.components.fab_button.ConversationFabButtonLayout
import com.example.messagesendingapp.user_conversations.presentation.components.ConversationUi
import com.example.messagesendingapp.user_conversations.presentation.components.bottom_bar.MessageAppBottomBar
import com.example.messagesendingapp.user_conversations.presentation.components.dialog_boxes.PhotoDialogBox
import com.example.messagesendingapp.user_conversations.presentation.components.style
import com.example.messagesendingapp.user_conversations.presentation.components.top_bar.long_pressed_top_bar.LongPressedTopBar
import com.example.messagesendingapp.user_conversations.presentation.components.top_bar.normal_top_bar.NormalTopBar
import com.example.messagesendingapp.user_conversations.presentation.components.top_bar.search_top_bar.SearchTopBar
import com.example.messagesendingapp.user_conversations.presentation.models.ConversationUiModel

//Screen for displaying all conversations
@Composable
fun ConversationScreen(
    modifier: Modifier = Modifier,
    state: ConversationScreenState,
    style: ConversationScreenStyle,
    actions: (ConversationActions) -> Unit,
    showIconsStatus: (Int) -> Int // Check viewmodel for this method
){
    //Matching the theme
    val conversationBackgroundColor = if(isSystemInDarkTheme()){
        secondaryContainerLightHighContrast

    }else{
        secondaryContainerLight
    }

    val searchBoxColor = if (isSystemInDarkTheme()){
        surfaceBrightDarkMediumContrast
    }else{
        outlineVariantDarkMediumContrast
    }
    val searchBoxContentColor = if (isSystemInDarkTheme()){
        Color.White
    }else{
        Color.Black
    }

    //Fab button color
    val fabButtonColor = if (isSystemInDarkTheme()){
        primaryContainerDarkMediumContrast
    }else{
        inversePrimaryDarkHighContrast
    }

    //search bar interaction source
    val searchInteraction = remember {
        MutableInteractionSource()
    }


    Scaffold (
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            if (!state.isUserSearching){
                if (state.noOfSelectedConversations > 0 && state.isUserSearching == false) {
                    LongPressedTopBar(state = state, actions = actions, showIconsStatus = showIconsStatus)
                } else if (state.isUserSearching == false) {
                    NormalTopBar(state = state, actions = actions)
                }
            }
        },
        bottomBar = {
            if (!state.isUserSearching){
                MessageAppBottomBar(state = state, action = actions)
            }
        },
        floatingActionButton = {
            if (!state.isUserSearching){
                ConversationFabButtonLayout(
                    buttonIcon = R.drawable.baseline_contacts_24,
                    actions = actions,
                    modifier = Modifier.size(70.dp),
                    backgroundColor = fabButtonColor
                )
            }
        }
    ){innerPadding->
        //Show the search box if isUserSearch is true

        if (state.isProfilePhotoEnlarged && state.selectedProfilePhotoId != null){
            PhotoDialogBox(conversation = state.conversations.find { it.id == state.selectedProfilePhotoId } ?: ConversationUiModel(), actions = actions)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(conversationBackgroundColor)
        ){
            if (state.isUserSearching) {
                SearchTopBar(state = state, actions = actions, background = conversationBackgroundColor)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (!state.isUserSearching){
                    item {
                        //Search tab
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(searchBoxColor)
                                .border(1.dp, Color.Black, RoundedCornerShape(50.dp))
                                .clickable(
                                    interactionSource = searchInteraction,
                                    indication = null,
                                    onClick = {
                                        actions(ConversationActions.onSearchBoxClicked)
                                    }
                                ),
                            contentAlignment = Alignment.TopStart

                        ){
                            Text(
                                modifier = Modifier.offset (x = 12.dp, y = 15.dp),
                                text = "Search Here",
                                color = searchBoxContentColor,
                                fontWeight = FontWeight.Light,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

                items(state.conversations.size){

                    ConversationUi(
                        style = style,
                        conversationUiModel = state.conversations[it],
                        actions = actions,
                        state = state
                    )

                }

            }
        }

    }
}

@Preview
@Composable
fun ConversationScreenPreview(){
    MessageSendingAppTheme {
        ConversationScreen(state = ConversationScreenState(), style = style , actions = {}, showIconsStatus = {1})
    }

}