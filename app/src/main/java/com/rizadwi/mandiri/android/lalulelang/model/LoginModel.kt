package com.rizadwi.mandiri.android.lalulelang.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    val token: String,
    val role: String
) : Parcelable