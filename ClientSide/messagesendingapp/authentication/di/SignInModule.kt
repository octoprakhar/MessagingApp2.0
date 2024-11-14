package com.example.messagesendingapp.authentication.di

import com.example.messagesendingapp.authentication.data.Networking.CloudSignIn
import com.example.messagesendingapp.authentication.domain.CloudRegistrationAbstract
import com.example.messagesendingapp.authentication.domain.SignInAbstract
import com.example.messagesendingapp.authentication.presentation.signIn.SignInViewModel
import com.example.messagesendingapp.core.data.networking.FirebaseAuthenticationHelper
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signInModule = module {

    single { FirebaseAuthenticationHelper() }

    //Bind cloud registration with cloud registration abstract
    singleOf(::CloudSignIn).bind<SignInAbstract>()

    viewModelOf(::SignInViewModel)
}