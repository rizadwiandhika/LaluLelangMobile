package com.rizadwi.mandiri.android.lalulelang.util.extension

import android.util.Log
import com.google.gson.Gson
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Failure
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import retrofit2.Response

inline fun <reified T : Any> Response<T>.getResult(): ResourceResult<T> {
    if (isSuccessful) {
        val data: T = this.body()!!
        return ResourceResult.Success(data)
    }

    val errorBody = errorBody()?.string()
    val errorData = Gson().fromJson(errorBody, Failure::class.java)

    Log.d("riza", errorBody ?: "Unknown error!")

    return ResourceResult.Failure(Error(errorData.reason ?: errorData.error))
}