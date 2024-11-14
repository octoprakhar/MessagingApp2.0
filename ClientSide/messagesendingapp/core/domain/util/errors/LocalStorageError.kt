package com.example.messagesendingapp.core.domain.util.errors

import com.example.messagesendingapp.core.domain.util.Error

enum class LocalStorageError: Error {
    INSERTION_FAILED,
    NAME_UPDATION_FAILED,
    EMAIL_UPDATION_FAILED,
    PASSWORD_UPDATION_FAILED,
    TOKEN_UPDATION_FAILED,
    PHONE_NUMBER_UPDATION_FAILED,
    NAME_CANNOT_BE_FETCHED,
    EMAIL_CANNOT_BE_FETCHED,
    PASSWORD_CANNOT_BE_FETCHED,
    TOKEN_CANNOT_BE_FETCHED,
    PHONE_NUMBER_CANNOT_BE_FETCHED,
    DELETION_FAILED
}