package com.example.messagesendingapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.compose.MessageSendingAppTheme
import com.example.messagesendingapp.authentication.presentation.AuthenticationEvent
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationViewModel
import com.example.messagesendingapp.authentication.presentation.signIn.SignInViewModel
import com.example.messagesendingapp.core.presentation.util.ObserveAsEvents
import com.example.messagesendingapp.core.presentation.util.toString
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageSendingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val registrationViewModel = koinViewModel<RegistrationViewModel>()
                    val signInViewModel = koinViewModel<SignInViewModel>()
                    val signInEvent = signInViewModel.events
                    val context = LocalContext.current

                    //Events for sign in screen
                    ObserveAsEvents(events = signInEvent) {event->
                        when(event){
                            is AuthenticationEvent.NetworkErrorInRegistration -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            is AuthenticationEvent.LocalStorageErrorInRegistration -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            is AuthenticationEvent.OtherRegistrationRelatedErrors -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }


                    //Events for registration screen
                    ObserveAsEvents(events = registrationViewModel.events) {event->
                        when(event){
                            is AuthenticationEvent.NetworkErrorInRegistration -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            is AuthenticationEvent.LocalStorageErrorInRegistration -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            is AuthenticationEvent.OtherRegistrationRelatedErrors -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }
                    AppNavigation(
                        signInViewModel = signInViewModel,
                        registrationViewModel = registrationViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

