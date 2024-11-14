package com.example.messagesendingapp.core.data.networking

import android.util.Log
import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.UserDetails
import com.example.messagesendingapp.core.domain.util.errors.NetworkError
import com.example.messagesendingapp.core.domain.util.errors.RegistrationOrSignInError
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class FirestoreHelper {

    private val TAG = "FirestoreHelper"
    private val _db = Firebase.firestore

    // Inserting a user
    suspend fun insertUser(
        userName: String,
        phoneNumber: String,
        email: String,
        password: String,
        userToken: String
    ): Result<Unit, Error> = suspendCancellableCoroutine { continuation ->

        // First make variables for userName and remote token
        val userNameQuery = _db.collection("users")
            .whereEqualTo("userName", userName.lowercase(Locale.ROOT))
        val remoteTokenQuery = _db.collection("users")
            .whereEqualTo("remoteToken", userToken)

        // First check if user with the same name exists
        userNameQuery.get()
            .addOnSuccessListener { userNameSnapShot ->
                if (!continuation.isCompleted && !userNameSnapShot.isEmpty) {
                    continuation.resume(Result.Error(RegistrationOrSignInError.DUPLICATE_USERNAME))
                } else {
                    // Now check if a user with the same token exists
                    remoteTokenQuery.get()
                        .addOnSuccessListener { remoteTokenSnapShot ->
                            if (!continuation.isCompleted && !remoteTokenSnapShot.isEmpty) {
                                continuation.resume(Result.Error(RegistrationOrSignInError.DEVICE_ALREADY_REGISTERED))
                            } else {
                                // If both pass, add new user
                                val user = hashMapOf(
                                    "userNameNew" to userName.lowercase(Locale.ROOT),
                                    "email" to email,
                                    "password" to password,
                                    "number" to phoneNumber,
                                    "remoteToken" to userToken
                                )
                                _db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener { documentReference ->
                                        if (!continuation.isCompleted) {
                                            continuation.resume(Result.Success(Unit))
                                        }
                                    }
                                    .addOnFailureListener { e ->
                                        if (!continuation.isCompleted) {
                                            when (e) {
                                                is FirebaseNetworkException -> {
                                                    continuation.resume(Result.Error(NetworkError.NO_INTERNET_CONNECTION))
                                                }
                                                else -> {
                                                    Log.d(TAG, "Error occurred while inserting user in Firestore: ${e.message}")
                                                    continuation.resume(Result.Error(RegistrationOrSignInError.CLOUD_STORAGE_FAILURE))
                                                }
                                            }
                                        }
                                    }
                            }
                        }
                        .addOnFailureListener { e ->
                            if (!continuation.isCompleted) {
                                when (e) {
                                    is FirebaseNetworkException -> {
                                        continuation.resume(Result.Error(NetworkError.NO_INTERNET_CONNECTION))
                                    }
                                    else -> {
                                        Log.d(TAG, "Error occurred while checking remote token: ${e.message}")
                                        continuation.resume(Result.Error(RegistrationOrSignInError.CLOUD_STORAGE_FAILURE))
                                    }
                                }
                            }
                        }
                }
            }
            .addOnFailureListener { e ->
                if (!continuation.isCompleted) {
                    when (e) {
                        is FirebaseNetworkException -> {
                            continuation.resume(Result.Error(NetworkError.NO_INTERNET_CONNECTION))
                        }
                        else -> {
                            Log.d(TAG, "Error occurred while checking username: ${e.message}")
                            continuation.resume(Result.Error(RegistrationOrSignInError.CLOUD_STORAGE_FAILURE))
                        }
                    }
                }
            }
    }
}
