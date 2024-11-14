package com.example.messagesendingapp.authentication.data.Local

import com.example.messagesendingapp.authentication.data.Local.dao.UserDetailDao
import com.example.messagesendingapp.authentication.data.mappers.toUserDetailEntity
import com.example.messagesendingapp.authentication.domain.LocalRegistrationAbstract
import com.example.messagesendingapp.core.domain.util.Result
import com.example.messagesendingapp.core.domain.util.UserDetails
import com.example.messagesendingapp.core.domain.util.errors.LocalStorageError

class LocalRegistration(
    private val userDetailDao: UserDetailDao
) : LocalRegistrationAbstract {
    override suspend fun insertUserLocally(userDetails: UserDetails): Result<Unit, LocalStorageError> {
        val id = userDetailDao.insertOrUpdateUser(userDetails.toUserDetailEntity())
        return if (id > 0) {
            Result.Success(Unit)
        } else {
            Result.Error(LocalStorageError.INSERTION_FAILED)
        }
    }
}