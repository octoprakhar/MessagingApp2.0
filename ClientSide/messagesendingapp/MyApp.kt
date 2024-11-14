package com.example.messagesendingapp

import android.app.Application
import com.example.messagesendingapp.authentication.data.Local.Graph
import com.example.messagesendingapp.authentication.di.registrationModule
import com.example.messagesendingapp.authentication.di.signInModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //Initialize modules
        startKoin{
            androidContext(this@MyApp)
            androidLogger()

            modules(registrationModule, signInModule)
        }
//        Graph.init(this)
    }
}