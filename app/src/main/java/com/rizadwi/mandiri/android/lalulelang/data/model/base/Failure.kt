package com.rizadwi.mandiri.android.lalulelang.data.model.base

import com.google.gson.annotations.SerializedName

data class Failure(
    @SerializedName("message")
    val reason: String?,

    @SerializedName("details")
    val details: List<String>?,

    @SerializedName("error")
    val error: String?
)


