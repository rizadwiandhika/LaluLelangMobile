package com.rizadwi.mandiri.android.lalulelang.util

sealed class UIState<out T : Any> {
    data class Success<out T : Any>(val data: T) : UIState<T>()
    data class Error(val error: kotlin.Error) : UIState<Nothing>()

    data object Loading : UIState<Nothing>()
}