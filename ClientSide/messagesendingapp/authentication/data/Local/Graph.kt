package com.example.messagesendingapp.authentication.data.Local

import android.content.Context
import androidx.room.Room

object Graph {

    lateinit var database: UserDetailDatabase

    val localRegistration by lazy {
        LocalRegistration(
            userDetailDao = database.userDetailDao()
        )
    }

    fun init(context: Context){
        database = Room.databaseBuilder(context, UserDetailDatabase::class.java, "user_detail_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}