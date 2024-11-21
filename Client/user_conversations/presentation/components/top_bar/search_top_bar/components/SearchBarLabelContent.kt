package com.example.messagesendingapp.user_conversations.presentation.components.top_bar.search_top_bar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MessageSendingAppTheme
import com.example.messagesendingapp.R

@Composable
fun SearchBarLabelContent(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
    backgroundColor: Color,
    contentColor : Color,
    onClick: () -> Unit
){

    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier.wrapContentWidth().padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = ImageVector.vectorResource(icon), contentDescription = text)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, color = contentColor, modifier = Modifier.padding(end = 8.dp))
            
        }
    }

}

@Preview
@Composable
fun SearchBarLabelContentPreview(){
    MessageSendingAppTheme {
        SearchBarLabelContent(
            modifier = Modifier
                .height(50.dp)
                .width(130.dp),
            icon = R.drawable.archive_image,
            text = "example",
            backgroundColor = Color.LightGray,
            onClick = {},
            contentColor = Color.Black
        )
    }
}