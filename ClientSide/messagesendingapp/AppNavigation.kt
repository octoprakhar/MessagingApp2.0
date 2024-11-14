package com.example.messagesendingapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messagesendingapp.authentication.presentation.NavigationEvent
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationScreen
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationViewModel
import com.example.messagesendingapp.authentication.presentation.signIn.SignInScreen
import com.example.messagesendingapp.authentication.presentation.signIn.SignInViewModel
import com.example.messagesendingapp.core.presentation.Screens

@Composable
fun AppNavigation(
    registrationViewModel: RegistrationViewModel,
    signInViewModel: SignInViewModel,
    modifier: Modifier = Modifier
){

    //Making navController locally, although it is not perfect approach, but for now I will use it
    val navController = rememberNavController()

    //Observing the navigation event for Registration screen
    val state by registrationViewModel.state.collectAsStateWithLifecycle()

    //Observing the navigation event for SignIn screen
    val signInState by signInViewModel.state.collectAsStateWithLifecycle()

    //Handling the navigation events for registration event. And also resetting the state
    LaunchedEffect (state.navigationEvent){
        when(state.navigationEvent){
            NavigationEvent.None -> {
                //Just do nothing
            }
            NavigationEvent.ToHomeScreen -> {
                //Navigate to home screen. And remove all the previous screens from backstack
                navController.navigate(Screens.HomeScreen.route){
                    popUpTo(Screens.RegistrationScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                registrationViewModel.onNavigationEventHandled()
            }
            NavigationEvent.ToSignInScreen -> {
                //Navigate to sign in screen. And remove all the previous screens from backstack
                navController.navigate(Screens.SignInScreen.route){
                    popUpTo(Screens.RegistrationScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                registrationViewModel.onNavigationEventHandled()
            }

            NavigationEvent.ToRegistrationScreen -> {
                //Navigate to registration screen. And remove all the previous screens from backstack
                navController.navigate(Screens.RegistrationScreen.route){
                    popUpTo(Screens.SignInScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                registrationViewModel.onNavigationEventHandled()

            }
        }

    }

    //Handling the navigation events for registration event. And also resetting the state
    LaunchedEffect (signInState.navigationEvent){
        when(signInState.navigationEvent){
            NavigationEvent.None -> {
                //Just do nothing
            }
            NavigationEvent.ToHomeScreen -> {
                //Navigate to home screen. And remove all the previous screens from backstack
                navController.navigate(Screens.HomeScreen.route){
                    popUpTo(Screens.RegistrationScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                signInViewModel.onNavigationEventHandled()
            }
            NavigationEvent.ToSignInScreen -> {
                //Navigate to sign in screen. And remove all the previous screens from backstack
                navController.navigate(Screens.SignInScreen.route){
                    popUpTo(Screens.RegistrationScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                signInViewModel.onNavigationEventHandled()
            }

            NavigationEvent.ToRegistrationScreen -> {
                //Navigate to registration screen. And remove all the previous screens from backstack
                navController.navigate(Screens.RegistrationScreen.route){
                    popUpTo(Screens.SignInScreen.route) {inclusive = true}
                }

                //Resetting the navigation events
                signInViewModel.onNavigationEventHandled()

            }
        }

    }


    NavHost(navController = navController, startDestination = Screens.RegistrationScreen.route){
        composable(Screens.RegistrationScreen.route)  {
            RegistrationScreen(
                modifier = modifier,
                registrationState = state,
                registrationActions = registrationViewModel::onAction,
            )
        }
        composable(Screens.SignInScreen.route)  {
            //Composable for signin screen
            SignInScreen(
                modifier = modifier,
                signInState = signInState,
                signInAction = signInViewModel::onAction
            )
        }
        composable(Screens.HomeScreen.route)  {
            //Composable for Home Screen
        }
    }

}