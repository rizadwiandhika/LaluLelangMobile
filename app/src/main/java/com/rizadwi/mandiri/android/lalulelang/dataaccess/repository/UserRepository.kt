package com.rizadwi.mandiri.android.lalulelang.dataaccess.repository

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface UserRepository {
    suspend fun authenticate(data: LoginRequest): ResourceResult<Success<LoginResponse>>
    suspend fun register(data: RegisterUserRequest): ResourceResult<Success<RegisterUserResponse>>
    suspend fun saveToken(token: String): ResourceResult<Unit>
    suspend fun getToken(): ResourceResult<String>
    suspend fun deleteToken(): ResourceResult<Unit>
}