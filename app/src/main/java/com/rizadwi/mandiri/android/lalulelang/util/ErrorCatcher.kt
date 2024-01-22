package com.rizadwi.mandiri.android.lalulelang.util

import android.util.Log
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorCatcher @Inject constructor() {
    inline fun <reified T : Any> catchIfError(action: () -> ResourceResult<T>): ResourceResult<T> {
        return try {
            action.invoke()
        } catch (e: Throwable) {
            Log.d("riza", e.message ?: "Unknown error occurred")
            ResourceResult.Failure(Error(e.message))
        }
    }
}