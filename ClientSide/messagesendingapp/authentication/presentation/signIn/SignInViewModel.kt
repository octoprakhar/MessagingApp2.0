package com.example.messagesendingapp.authentication.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagesendingapp.authentication.domain.SignInAbstract
import com.example.messagesendingapp.authentication.presentation.NavigationEvent
import com.example.messagesendingapp.authentication.presentation.AuthenticationEvent
import com.example.messagesendingapp.core.domain.util.errors.NetworkError
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError
import com.example.messagesendingapp.core.domain.util.onError
import com.example.messagesendingapp.core.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInAbstract: SignInAbstract
): ViewModel() {

    private val TAG = "SignInViewModelResponse"

    //It manages the state of the screen
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    //Manages one time events
    private val _events = Channel<AuthenticationEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(actions: SignInAction){
        when(actions){
            SignInAction.SignInUser -> {
                viewModelScope.launch {
                    //Do authentication sign in
                    signInAbstract.signInUser(
                            email = _state.value.email,
                            password = _state.value.password
                    )
                        .onError { error->
                            //Handle sign in related errors
                            when(error){
                                RegistrationOrSignInError.USER_ALREADY_SIGNED_IN -> _events.send(
                                    AuthenticationEvent.OtherRegistrationRelatedErrors(error as RegistrationOrSignInError))
                                RegistrationOrSignInError.CLOUD_AUTHENTICATION_FAILURE -> _events.send(
                                    AuthenticationEvent.OtherRegistrationRelatedErrors(error as RegistrationOrSignInError))
                                RegistrationOrSignInError.INVALID_CREDENTIALS -> _events.send(
                                    AuthenticationEvent.OtherRegistrationRelatedErrors(error as RegistrationOrSignInError))
                                NetworkError.NO_INTERNET_CONNECTION -> _events.send(
                                    AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError))
                                NetworkError.SERIALIZATION -> _events.send(AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError))
                                RegistrationOrSignInError.DUPLICATE_USERNAME -> _events.send(
                                    AuthenticationEvent.OtherRegistrationRelatedErrors(error as RegistrationOrSignInError))
                                NetworkError.SERVER_ERROR -> _events.send(AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError))


                            }
                        }
                        .onSuccess {
                            //If everything goes right, navigate to home screen.
                            _state.value = _state.value.copy(
                                navigationEvent = NavigationEvent.ToHomeScreen
                            )
                        }
                }
            }
            SignInAction.onRegistrationClicked -> {
                // Update navigationEvent in _state only for navigation actions
                _state.value = _state.value.copy(navigationEvent = NavigationEvent.ToRegistrationScreen)

            }
            is SignInAction.SetEmail -> {
                _state.value = _state.value.copy(
                    email = actions.email
                )
            }
            is SignInAction.SetPassword -> {
                _state.value = _state.value.copy(
                    password = actions.password
                )
            }

        }
    }
    fun onNavigationEventHandled() {
        _state.value = _state.value.copy(navigationEvent = NavigationEvent.None)
    }

}