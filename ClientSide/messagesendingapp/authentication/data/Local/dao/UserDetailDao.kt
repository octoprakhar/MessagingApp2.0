package com.example.messagesendingapp.authentication.data.Local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.messagesendingapp.authentication.data.Local.entities.UserDetailEntity

@Dao
interface UserDetailDao {

    //Inserting and updating the userDetails
    @Upsert
    suspend fun insertOrUpdateUser(userDetailEntity: UserDetailEntity) : Long

}