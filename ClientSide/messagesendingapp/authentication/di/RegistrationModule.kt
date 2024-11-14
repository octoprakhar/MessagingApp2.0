package com.example.messagesendingapp.authentication.di

import androidx.room.Room
import com.example.messagesendingapp.authentication.data.Local.LocalRegistration
import com.example.messagesendingapp.authentication.data.Local.UserDetailDatabase
import com.example.messagesendingapp.authentication.data.Networking.CloudRegistration
import com.example.messagesendingapp.authentication.domain.CloudRegistrationAbstract
import com.example.messagesendingapp.authentication.domain.LocalRegistrationAbstract
import com.example.messagesendingapp.authentication.presentation.registration.RegistrationViewModel
import com.example.messagesendingapp.core.data.networking.FirebaseAuthenticationHelper
import com.example.messagesendingapp.core.data.networking.FirestoreHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.koinApplication
import org.koin.dsl.module

//All dependencies related to cloud is stored here

val registrationModule = module {
    //For cloud
    single { FirestoreHelper() }
    single { FirebaseAuthenticationHelper() }

    //Provide room database
    single {
        Room.databaseBuilder(get(), UserDetailDatabase::class.java, "user_detail_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    //Provide Dao
    single { get<UserDetailDatabase>().userDetailDao() }

    //Bind cloud registration with cloud registration abstract
    singleOf(::CloudRegistration).bind<CloudRegistrationAbstract>()

    //Bind local registration abstract to its implementation
    singleOf(::LocalRegistration).bind<LocalRegistrationAbstract>()

    viewModelOf(::RegistrationViewModel)

}

