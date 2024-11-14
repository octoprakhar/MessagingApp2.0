package com.example.messagesendingapp.core.domain.util

typealias DomainError = Error
typealias EmptyResult<E> = Result<Unit, E>

sealed interface Result<out D, out E: DomainError>{
    data class Success<out D>(val data: D) : Result<D,Nothing>
    data class Error<out E : DomainError>(val error : E) : Result<Nothing, E>
}

inline fun <T, E: DomainError,R> Result<T,E>.mapAResult(transform: (T) -> R): Result<R,E>{
    return when(this){
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> Result.Error(error)
    }
}

fun <T, E: DomainError> Result<T,E>.asEmptyDataResult(): EmptyResult<E>{
    return this.mapAResult {  }
}

inline fun <T, E: DomainError> Result<T,E>.onSuccess(action: (T)-> Unit): Result<T,E>{
    return when(this){
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }

    }
}

inline fun <T, E: DomainError> Result<T,E>.onError(action: (E)-> Unit): Result<T,E>{
    return when(this){
        is Result.Success -> this
        is Result.Error -> {
            action(error)
            this
        }

    }
}