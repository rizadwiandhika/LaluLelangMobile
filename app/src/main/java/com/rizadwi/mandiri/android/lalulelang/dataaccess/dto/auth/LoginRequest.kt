package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    val email: String,
    val password: String,
) : Parcelable