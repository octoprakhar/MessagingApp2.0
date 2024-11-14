package com.example.messagesendingapp.authentication.presentation.registration

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
import com.example.compose.primaryContainerLightMediumContrast
import com.example.compose.primaryLightMediumContrast
import com.example.compose.surfaceContainerLowLightMediumContrast

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    registrationState: RegistrationState,
    registrationActions: (RegistrationActions) -> Unit
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
                text = "Register",
                style = MaterialTheme.typography.headlineMedium,
                color = contentColor,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = registrationState.userName,
                onValueChange = { registrationActions(RegistrationActions.SetUserName(it)) },
                label = { Text("User Name", color = contentColor) },
                placeholder = { Text(text = "Enter a user name", color = contentColor, style = MaterialTheme.typography.labelSmall)},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = registrationState.email,
                onValueChange = { registrationActions(RegistrationActions.SetEmail(it)) },
                label = { Text("Email Id",color = contentColor) },
                placeholder = { Text(text = "Enter a email id",color = contentColor)},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = registrationState.password,
                onValueChange = { registrationActions(RegistrationActions.SetPassword(it)) },
                label = { Text("Password",color = contentColor) },
                placeholder = { Text(text = "Enter your password",color = contentColor)},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = registrationState.phoneNumber,
                onValueChange = { registrationActions(RegistrationActions.SetPhoneNumber(it)) },
                label = { Text("Phone Number",color = contentColor) },
                placeholder = { Text(text = "Enter Your Phone Number",color = contentColor)},
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
                    onClick = { registrationActions(RegistrationActions.RegisterUser) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Register")
                }

                TextButton(
                    onClick = { registrationActions(RegistrationActions.onSignInClicked) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Already have an account?")
                }
            }


        }
    }

}

@Preview
@Composable
fun RegistrationScreenPreview(){
    MessageSendingAppTheme {
        RegistrationScreen(registrationState = RegistrationState()) {
            
        }
    }
}