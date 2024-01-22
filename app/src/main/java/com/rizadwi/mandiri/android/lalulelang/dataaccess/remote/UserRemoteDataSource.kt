package com.rizadwi.mandiri.android.lalulelang.data.remote

import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface UserRemoteDataSource {

    suspend fun authenticate(request: LoginRequest): ResourceResult<Success<LoginResponse>>
    suspend fun register(request: RegisterUserRequest): ResourceResult<Success<RegisterUserResponse>>

}