package com.example.messagesendingapp.user_conversations.presentation.components.top_bar.normal_top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.inversePrimaryDarkHighContrast
import com.example.compose.onTertiaryContainerDark
import com.example.compose.outlineVariantDarkHighContrast
import com.example.compose.primaryContainerDarkMediumContrast
import com.example.compose.tertiaryDarkMediumContrast
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState
import com.example.messagesendingapp.user_conversations.presentation.components.drop_downs.NormalDropDown
import com.example.ui.theme.displayFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(
    modifier: Modifier = Modifier,
    state:ConversationScreenState,
    actions: (ConversationActions) -> Unit
){
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
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarBackgroundColor,
            scrolledContainerColor = topBarBackgroundColor
        ),
        title = {
        Text(
            text = "HOLA",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    },
        actions = {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable { },
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_camera_alt_24),
                contentDescription = "Camera"
            )
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
            if(state.isMoreOptionsVisible && state.selectedConversations.isEmpty()){
                Box (
                    modifier = Modifier.offset(y = 20.dp) // Offset to push it slightly below
//                        .background(topBarBackgroundColor)

                ){
                    NormalDropDown(state = state, actions = actions, backgroundColor = dropDownColor)
                }
            }

        })
}


@Preview
@Composable
fun NormalTopBarPreview(){
    NormalTopBar(state = ConversationScreenState(), actions = {})
}