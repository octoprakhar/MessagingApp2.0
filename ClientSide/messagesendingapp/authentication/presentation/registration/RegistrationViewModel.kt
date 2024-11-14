package com.example.messagesendingapp.authentication.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messagesendingapp.authentication.domain.CloudRegistrationAbstract
import com.example.messagesendingapp.authentication.domain.LocalRegistrationAbstract
import com.example.messagesendingapp.authentication.presentation.NavigationEvent
import com.example.messagesendingapp.authentication.presentation.AuthenticationEvent
import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.UserDetails
import com.example.messagesendingapp.core.domain.util.errors.NetworkError
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError
import com.example.messagesendingapp.core.domain.util.onError
import com.example.messagesendingapp.core.domain.util.onSuccess
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

//This handles all the UI related logic and also provide data to register abstract class.
class RegistrationViewModel(
    private val cloudRegistrationAbstract: CloudRegistrationAbstract,
    private val localRegistrationAbstract: LocalRegistrationAbstract
) : ViewModel() {

    private val TAG = "RegistrationViewModelResponse"

    //It manages the state of the screen
    private val _state = MutableStateFlow(RegistrationState())
    val state = _state.asStateFlow()

    //Manages one time events
    private val _events = Channel<AuthenticationEvent>()
    val events = _events.receiveAsFlow()

    //All the actions that can be taken inside registration screen
    fun onAction(actions: RegistrationActions) {
        when (actions) {
            RegistrationActions.RegisterUser -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val userToken = FirebaseMessaging.getInstance().token.await()

                    if (userToken != null) {
                        //Saving user to cloud firebase authentication
                        cloudRegistrationAbstract.registerUserInAuth(
                            UserDetails(
                                userName = _state.value.userName,
                                email = _state.value.email,
                                password = _state.value.password,
                                phoneNumber = _state.value.phoneNumber,
                                userToken = userToken
                            )
                        )
                            .onError { error: Error ->
                                Log.d(TAG, "onAction: $error")
                                when (error) {
                                    RegistrationOrSignInError.USER_ALREADY_SIGNED_IN -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.CLOUD_AUTHENTICATION_FAILURE -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.INVALID_CREDENTIALS -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    NetworkError.NO_INTERNET_CONNECTION -> _events.send(
                                        AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError)
                                    )

                                    NetworkError.SERIALIZATION -> _events.send(
                                        AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError)
                                    )

                                    RegistrationOrSignInError.DUPLICATE_USERNAME -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    NetworkError.SERVER_ERROR -> _events.send(
                                        AuthenticationEvent.NetworkErrorInRegistration(error as NetworkError)
                                    )

                                    RegistrationOrSignInError.DEVICE_ALREADY_REGISTERED -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.PASSWORD_TOO_SHORT -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.INVALID_USERNAME_FORMAT -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.USERNAME_TOO_SHORT -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.INVALID_PHONE_NUMBER -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.INVALID_EMAIL_ADDRESS -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                    RegistrationOrSignInError.INVALID_EMAIL_FORMAT -> _events.send(
                                        AuthenticationEvent.OtherRegistrationRelatedErrors(
                                            error as RegistrationOrSignInError
                                        )
                                    )

                                }

                            }
                            .onSuccess {
                                //Saving user in firestore
                                cloudRegistrationAbstract.registerUserInFireStore(
                                    UserDetails(
                                        userName = _state.value.userName,
                                        password = _state.value.password,
                                        userToken = userToken,
                                        email = _state.value.email,
                                        phoneNumber = _state.value.phoneNumber
                                    )
                                )
                                    .onError { error: Error ->
                                        when (error) {
                                            RegistrationOrSignInError.DUPLICATE_USERNAME -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.DEVICE_ALREADY_REGISTERED -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.CLOUD_STORAGE_FAILURE -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            NetworkError.NO_INTERNET_CONNECTION -> _events.send(
                                                AuthenticationEvent.NetworkErrorInRegistration(
                                                    error as NetworkError
                                                )
                                            )

                                            NetworkError.SERVER_ERROR -> _events.send(
                                                AuthenticationEvent.NetworkErrorInRegistration(
                                                    error as NetworkError
                                                )
                                            )

                                            RegistrationOrSignInError.PASSWORD_TOO_SHORT -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.PASSWORD_WITHOUT_UPPERCASE_NUMBER_SPECIAL_CHARACTERS -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.INVALID_USERNAME_FORMAT -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.USERNAME_TOO_SHORT -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.INVALID_PHONE_NUMBER -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.INVALID_EMAIL_ADDRESS -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )

                                            RegistrationOrSignInError.INVALID_EMAIL_FORMAT -> _events.send(
                                                AuthenticationEvent.OtherRegistrationRelatedErrors(
                                                    error as RegistrationOrSignInError
                                                )
                                            )


                                        }
                                    }
                                    .onSuccess {
                                        //Saving user to locally
                                        localRegistrationAbstract
                                            .insertUserLocally(
                                                UserDetails(
                                                    userName = _state.value.userName,
                                                    userToken = userToken,
                                                    password = _state.value.password,
                                                    email = _state.value.email,
                                                    phoneNumber = _state.value.phoneNumber
                                                )
                                            )
                                            .onError { localStorageError ->
                                                _events.send(
                                                    AuthenticationEvent.LocalStorageErrorInRegistration(
                                                        error = localStorageError
                                                    )
                                                )
                                            }
                                            .onSuccess {
                                                //If everything goes right, navigate to home screen.
                                                _state.value = _state.value.copy(
                                                    navigationEvent = NavigationEvent.ToHomeScreen
                                                )
                                            }
                                    }
                            }
                    }
                }


            }

            is RegistrationActions.SetEmail -> {
                _state.value = _state.value.copy(
                    email = actions.email
                )
            }

            is RegistrationActions.SetPassword -> {
                _state.value = _state.value.copy(
                    password = actions.password
                )
            }

            is RegistrationActions.SetPhoneNumber -> {
                _state.value = _state.value.copy(
                    phoneNumber = actions.phoneNumber
                )
            }

            is RegistrationActions.SetUserName -> {
                _state.value = _state.value.copy(
                    userName = actions.userName
                )
            }

            //Trigger the event to navigate to signIn screen
            RegistrationActions.onSignInClicked -> {
                // Update navigationEvent in _state only for navigation actions
                _state.value = _state.value.copy(navigationEvent = NavigationEvent.ToSignInScreen)
            }
        }
    }

    fun onNavigationEventHandled() {
        _state.value = _state.value.copy(navigationEvent = NavigationEvent.None)
    }

}