package com.example.messagesendingapp.authentication.data.Networking

import com.example.messagesendingapp.authentication.data.Networking.dto.RegistrationDto
import com.example.messagesendingapp.authentication.data.mappers.toRegistrationDto
import com.example.messagesendingapp.authentication.domain.CloudRegistrationAbstract
import com.example.messagesendingapp.authentication.data.util.RegistrationCredentialsChecker
import com.example.messagesendingapp.core.data.networking.CoreSingletons
import com.example.messagesendingapp.core.data.networking.FirebaseAuthenticationHelper
import com.example.messagesendingapp.core.data.networking.FirestoreHelper
import com.example.messagesendingapp.core.domain.util.UserDetails
import com.example.messagesendingapp.core.domain.util.Error
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.onError
import com.example.messagesendingapp.core.domain.util.onSuccess

class CloudRegistration(
    private val firestoreHelper: FirestoreHelper,
    private val firebaseAuthenticationHelper: FirebaseAuthenticationHelper
) : CloudRegistrationAbstract {
    override suspend fun registerUserInFireStore(userDetails: UserDetails): Result<Unit, Error> {
        val registrationDto = userDetails.toRegistrationDto()

        //First locally check some basic things before sending to cloud
        return RegistrationCredentialsChecker(registrationDto)
            .onSuccess {
                return firestoreHelper
                    .insertUser(
                        userName = registrationDto.userName,
                        password = registrationDto.password,
                        email = registrationDto.email,
                        phoneNumber = registrationDto.phoneNumber,
                        userToken = registrationDto.userToken
                    )
                    .onSuccess {
                        return Result.Success(Unit)
                    }
                    .onError {
                        return Result.Error(it)
                    }
            }
            .onError {
                return Result.Error(it)
            }


    }

    override suspend fun registerUserInAuth(userDetails: UserDetails): Result<Unit, Error> {
        val registrationDto = userDetails.toRegistrationDto()

        //First locally check some basic things before sending to cloud
        return RegistrationCredentialsChecker(registrationDto)
            .onSuccess {
                firebaseAuthenticationHelper
                    .RegisterNewUser(registrationDto.email, registrationDto.password)
                    .onSuccess {
                        return Result.Success(Unit)
                    }
                    .onError {
                        return Result.Error(it)
                    }
            }
            .onError {
                return Result.Error(it)
            }
    }
}