package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("role")
    val role: String
)