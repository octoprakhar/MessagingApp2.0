package com.example.messagesendingapp.user_conversations.presentation.components.bottom_bar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.compose.onTertiaryContainerDark
import com.example.compose.outlineVariantDarkMediumContrast
import com.example.compose.tertiaryContainerDarkMediumContrast
import com.example.compose.tertiaryDarkMediumContrast
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState

@Composable
fun MessageAppBottomBar(
    action: (ConversationActions) -> Unit,
    state: ConversationScreenState
){

    //Matching the theme
    val bottomBarBackgroundColor = if(isSystemInDarkTheme()){
        onTertiaryContainerDark

    }else{
        tertiaryDarkMediumContrast
    }

    val clickedContainerColor = if (isSystemInDarkTheme()){
        outlineVariantDarkMediumContrast
    } else{
        tertiaryContainerDarkMediumContrast
    }

    //Importing button clicked state
    val isButtonClicked = state.bottomBarButtonClicked

    BottomAppBar(
        containerColor = bottomBarBackgroundColor,
        actions = {
            repeat(4) { index ->
                BottomBarContentLayout(
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    icon = when (index){
                        0-> R.drawable.baseline_home_24
                        1 -> R.drawable.baseline_upcoming_24
                        2 -> R.drawable.baseline_person_add_24
                        else -> R.drawable.baseline_call_24
                    },
                    title = when (index){
                        0 -> "Home"
                        1 -> "Update"
                        2 -> "group"
                        else -> "Calls"
                    },
                    textWeight =if (isButtonClicked == index) FontWeight.Bold else FontWeight.Normal,
                    onClick = {

                        action(ConversationActions.onBottomBarButtonClicked(index))
                              },
                    backgroundColor = if (isButtonClicked == index) clickedContainerColor else Color.Transparent
                )
            }
    })

}