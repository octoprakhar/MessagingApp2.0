package com.example.messagesendingapp.core.domain.util.errors

import com.example.messagesendingapp.core.domain.util.Error

enum class NetworkError: Error {
    NO_INTERNET_CONNECTION,
    USER_NOT_FOUND,
    SERVER_ERROR,
    MESSAGE_TIMEOUT,
    TOO_MANY_MESSAGES,
    SERIALIZATION,
    UNKNOWN_ERROR
}