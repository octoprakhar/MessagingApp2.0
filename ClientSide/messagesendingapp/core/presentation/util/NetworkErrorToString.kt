package com.example.messagesendingapp.core.presentation.util

import android.content.Context
import com.example.messagesendingapp.R
import com.example.messagesendingapp.core.domain.util.errors.NetworkError

fun NetworkError.toString(context: Context) : String{
    val resId = when(this){
        NetworkError.NO_INTERNET_CONNECTION -> R.string.NoInternetConnection
        NetworkError.USER_NOT_FOUND -> R.string.UserNotFound
        NetworkError.SERVER_ERROR -> R.string.ServerError
        NetworkError.MESSAGE_TIMEOUT -> R.string.MessageTimeout
        NetworkError.TOO_MANY_MESSAGES -> R.string.TooManyMessages
        NetworkError.SERIALIZATION -> R.string.Serialization
        NetworkError.UNKNOWN_ERROR -> R.string.UnknownError
    }
    return context.getString(resId)
}