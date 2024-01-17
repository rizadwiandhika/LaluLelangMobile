package com.rizadwi.mandiri.android.lalulelang.data.model.base

import com.google.gson.annotations.SerializedName

data class Success<T>(
    @SerializedName("message")
    val message: String,

    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("data")
    val data: T?
)