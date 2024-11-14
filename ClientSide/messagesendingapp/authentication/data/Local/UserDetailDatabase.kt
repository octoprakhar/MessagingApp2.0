package com.example.messagesendingapp.authentication.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.messagesendingapp.authentication.data.Local.dao.UserDetailDao
import com.example.messagesendingapp.authentication.data.Local.entities.UserDetailEntity
import com.example.messagesendingapp.core.domain.util.UserDetails

@Database(
    entities = [UserDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDetailDatabase : RoomDatabase(){
    abstract fun userDetailDao() : UserDetailDao
}