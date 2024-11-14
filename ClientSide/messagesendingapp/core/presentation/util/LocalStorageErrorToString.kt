package com.example.messagesendingapp.core.presentation.util

import android.content.Context
import com.example.messagesendingapp.R
import com.example.messagesendingapp.core.domain.util.errors.LocalStorageError

fun LocalStorageError.toString(context: Context): String{
    val resId = when(this){
        LocalStorageError.INSERTION_FAILED -> R.string.InsertionFailed
        LocalStorageError.NAME_UPDATION_FAILED -> R.string.NameUpdateFailed
        LocalStorageError.EMAIL_UPDATION_FAILED -> R.string.EmailUpdateFailed
        LocalStorageError.PASSWORD_UPDATION_FAILED -> R.string.PasswordUpdateFailed
        LocalStorageError.TOKEN_UPDATION_FAILED -> R.string.TokenUpdateFailed
        LocalStorageError.PHONE_NUMBER_UPDATION_FAILED -> R.string.PhoneNumberUpdateFailed
        LocalStorageError.NAME_CANNOT_BE_FETCHED -> R.string.NameFetchFailed
        LocalStorageError.EMAIL_CANNOT_BE_FETCHED -> R.string.EmailFetchFailed
        LocalStorageError.PASSWORD_CANNOT_BE_FETCHED -> R.string.PasswordFetchFailed
        LocalStorageError.TOKEN_CANNOT_BE_FETCHED -> R.string.TokenFetchFailed
        LocalStorageError.PHONE_NUMBER_CANNOT_BE_FETCHED -> R.string.PhoneNumberFetchFailed
        LocalStorageError.DELETION_FAILED -> R.string.DeletionFailed
    }

    return context.getString(resId)
}