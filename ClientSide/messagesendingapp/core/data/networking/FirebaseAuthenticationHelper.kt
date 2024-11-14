package com.example.messagesendingapp.core.data.networking

import android.app.Activity
import android.util.Log
import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.errors.NetworkError
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseAuthenticationHelper {

    private val TAG = "FirebaseAuthenticationHelper"
    private val auth: FirebaseAuth = Firebase.auth

    // Checking Whether user is already signed In or not
    fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    // Function to sign out
    fun signOut() {
        auth.signOut()
    }

    // Registering the user in firebase authentication
    suspend fun RegisterNewUser(email: String, password: String): Result<Unit, Error> =
        suspendCancellableCoroutine { continuation ->
            if (isUserSignedIn()) {
                // Avoid resuming more than once
                if (!continuation.isCompleted) {
                    continuation.resume(Result.Error(RegistrationOrSignInError.USER_ALREADY_SIGNED_IN))
                }
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        val exception = task.exception
                        if (!continuation.isCompleted) {
                            when (exception) {
                                is FirebaseNetworkException -> continuation.resume(Result.Error(NetworkError.NO_INTERNET_CONNECTION))
                                is FirebaseAuthInvalidCredentialsException -> continuation.resume(Result.Error(NetworkError.SERIALIZATION)) // Invalid input, like weak password
                                is FirebaseAuthUserCollisionException -> continuation.resume(Result.Error(RegistrationOrSignInError.DUPLICATE_USERNAME)) // Account already exists
                                is FirebaseAuthException -> continuation.resume(Result.Error(NetworkError.SERVER_ERROR))
//                                else -> continuation.resume(Result.Error(RegistrationOrSignInError.CLOUD_AUTHENTICATION_FAILURE))
                                else ->{Log.d(TAG,"${exception?.message}")}
                            }
                        }

                        if (task.isSuccessful && !continuation.isCompleted) {
                            continuation.resume(Result.Success(Unit))
                        }
                    }
            }
        }

    // Sign in the user in firebase authentication
    suspend fun SignInUser(email: String, password: String): Result<Unit, Error> =
        suspendCancellableCoroutine { continuation ->
            Log.d(TAG, "Current user: ${auth.currentUser}")
            if (isUserSignedIn()) {
                if (!continuation.isCompleted) {
                    Log.d(TAG, "User already signed in.")
                    continuation.resume(Result.Error(RegistrationOrSignInError.USER_ALREADY_SIGNED_IN))
                }
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        val exception = task.exception
                        if (!continuation.isCompleted) {
                            when (exception) {
                                is FirebaseNetworkException -> continuation.resume(Result.Error(NetworkError.NO_INTERNET_CONNECTION))
                                is FirebaseAuthInvalidCredentialsException -> continuation.resume(Result.Error(RegistrationOrSignInError.INVALID_CREDENTIALS))
                                is FirebaseAuthInvalidUserException -> continuation.resume(Result.Error(NetworkError.USER_NOT_FOUND))
                                is FirebaseAuthException -> continuation.resume(Result.Error(NetworkError.SERVER_ERROR))
                                else ->{Log.d(TAG,"${exception?.message}")}                            }
                        }
                        if (task.isSuccessful && !continuation.isCompleted) {
                            Log.d(TAG, "Sign in successful.")
                            continuation.resume(Result.Success(Unit))
                        }
                    }
            }
        }

}
