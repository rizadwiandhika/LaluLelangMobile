package com.rizadwi.mandiri.android.lalulelang.util.data

sealed class ResourceResult<out T : Any> {
    data class Success<out T : Any>(val payload: T) : ResourceResult<T>()
    data class Failure(val cause: Error) :
        ResourceResult<Nothing>()
}