package com.example.messagesendingapp.authentication.presentation.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MessageSendingAppTheme
import com.example.compose.inversePrimaryDarkMediumContrast
import com.example.compose.inversePrimaryLightMediumContrast
import com.example.compose.onPrimaryDark
import com.example.compose.surfaceContainerLowLightMediumContrast
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationActions
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationScreen
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationState

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    signInState: SignInState,
    signInAction: (SignInAction) -> Unit
){
    //Colors and themes
    val contentColor = if (isSystemInDarkTheme()){
        Color.White
    }else{
        Color.Black
    }
    val backgroundColor = if (isSystemInDarkTheme()){
        inversePrimaryLightMediumContrast
    }else{
        surfaceContainerLowLightMediumContrast
    }

    val buttonBackgroundColor = if (isSystemInDarkTheme()){
        onPrimaryDark
    }else{
        inversePrimaryDarkMediumContrast
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(32.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineMedium,
                color = contentColor,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )


            OutlinedTextField(
                value = signInState.email,
                onValueChange = { signInAction(SignInAction.SetEmail(it)) },
                label = { Text("Email Id",color = contentColor) },
                placeholder = { Text(text = "Enter a email id",color = contentColor) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = signInState.password,
                onValueChange = { signInAction(SignInAction.SetPassword(it)) },
                label = { Text("Password",color = contentColor) },
                placeholder = { Text(text = "Enter your password",color = contentColor) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )

            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    onClick = { signInAction(SignInAction.SignInUser) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Sign In")
                }

                TextButton(
                    onClick = { signInAction(SignInAction.onRegistrationClicked) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Don't have an account?")
                }
            }


        }
    }

}

@Preview
@Composable
fun SignInScreenPreview(){
    MessageSendingAppTheme {
        SignInScreen(signInState = SignInState()) {

        }
    }
}