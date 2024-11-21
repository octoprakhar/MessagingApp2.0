package com.example.messagesendingapp.user_conversations.presentation.components.top_bar.search_top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.MessageSendingAppTheme
import com.example.compose.onTertiaryContainerDark
import com.example.compose.outlineVariantDark
import com.example.compose.surfaceVariantDarkHighContrast
import com.example.compose.tertiaryDarkMediumContrast
import com.example.messagesendingapp.R
import com.example.messagesendingapp.user_conversations.presentation.ConversationActions
import com.example.messagesendingapp.user_conversations.presentation.ConversationScreenState
import com.example.messagesendingapp.user_conversations.presentation.components.top_bar.search_top_bar.components.SearchBarLabelContent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    state: ConversationScreenState,
    actions: (ConversationActions) -> Unit,
    background: Color = Color.White
){
    //Making the theme
    val highlightedBackgroundColor = if (isSystemInDarkTheme()){
        surfaceVariantDarkHighContrast
    }else{
        Color.LightGray
    }

    val highlightedContentColor = if (isSystemInDarkTheme()){
        Color.White
    }else{
        Color.Black
    }

    //Matching the theme
    val selectedSearchTagBackgroundColor = if(isSystemInDarkTheme()){
        onTertiaryContainerDark

    }else{
        tertiaryDarkMediumContrast
    }

    //Search label text list
    val searchLabelTexts = listOf("Unread","Photos","Videos","Links","GIFs","Audio","Documents")
    val searchLabelIcons = listOf(R.drawable.unread_message,R.drawable.image_icon, R.drawable.video_icon, R.drawable.link_icon, R.drawable.gif_image, R.drawable.audio_icon, R.drawable.document_icon)

    // Create a FocusRequester
    val focusRequester = remember { FocusRequester() }

    // Request focus and show the keyboard when the TextField is visible
    LaunchedEffect(state.isUserSearching) {
        if (state.isUserSearching) {
            focusRequester.requestFocus()
        }
    }


    //Getting search tag index number
    var searchTagIndex by remember {
        mutableStateOf(state.indexOfTagSelected)
    }

    LaunchedEffect(state.indexOfTagSelected) {
        searchTagIndex = state.indexOfTagSelected
    }



    // Pinned Box at the top
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(highlightedBackgroundColor)

                    ,
                contentAlignment = Alignment.TopStart

            ){

                    Row (
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .padding(end = 8.dp)
                                .clickable {
                                    actions(ConversationActions.onSearchBackButtonPressed)

                                },
                            imageVector = ImageVector.vectorResource(id = R.drawable.nav_back_image),
                            contentDescription = "back",
                            tint = highlightedContentColor
                        )

                        if (searchTagIndex != -1){
                            SearchBarLabelContent(
                                icon = searchLabelIcons[searchTagIndex],
                                text = searchLabelTexts[searchTagIndex],
                                backgroundColor = selectedSearchTagBackgroundColor,
                                contentColor = highlightedContentColor,
                                onClick = {}
                            )
                        }

                        TextField(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .focusRequester(focusRequester),
                            value = state.searchText,
                            onValueChange = { actions(ConversationActions.onSearchTextChanges(it)) },
                            placeholder = {
                                Text(
                                    text = "Search Here",
                                    color = highlightedContentColor,
                                    fontSize = if (state.indexOfTagSelected == -1) 32.sp else 16.sp
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = highlightedBackgroundColor,
                                unfocusedContainerColor = highlightedBackgroundColor,
                                focusedTextColor = highlightedContentColor,
                                unfocusedTextColor = highlightedContentColor
                            ),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = if (state.indexOfTagSelected == -1) 32.sp else 16.sp
                            ),
                            singleLine = true
                        )
                        if (searchTagIndex != -1){
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "cancel",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(end = 8.dp)
                                    .clickable {
                                        actions(ConversationActions.onUncheckSearchTag)

                                                                            }
                            )
                        }

                    }



            }
                    FlowRow(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .fillMaxWidth() // Ensure it takes available width
                    ){
                        searchLabelIcons.forEachIndexed { index, icon->
                            SearchBarLabelContent(
                                modifier = Modifier
                                    .height(50.dp)
                                    .wrapContentWidth()
                                    .padding(vertical = 4.dp),
                                icon = icon,
                                text = searchLabelTexts[index],
                                backgroundColor = highlightedBackgroundColor,
                                contentColor = highlightedContentColor,
                                onClick = {
                                    actions(ConversationActions.onSearchTagWithIndexClicked(index))
                                }
                            )

                        }


                    }
        }
    }
}

@Preview
@Composable
fun SearchTopBarPreview(){
    MessageSendingAppTheme {
        SearchTopBar(
//            modifier = Modifier.offset (y = 80.dp),
            state = ConversationScreenState(),
            actions = {},
            background = Color.Cyan
        )
    }
}