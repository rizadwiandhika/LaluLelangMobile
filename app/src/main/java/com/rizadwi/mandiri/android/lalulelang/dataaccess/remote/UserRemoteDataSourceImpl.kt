package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.service.LaluLelangService
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.extension.getResult
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val service: LaluLelangService) :
    UserRemoteDataSource {

    override suspend fun authenticate(request: LoginRequest): ResourceResult<Success<LoginResponse>> {
        return try {
            service.authenticate(request).getResult()
        } catch (e: Throwable) {
            ResourceResult.Failure(Error(e.message, e))
        }
    }

    override suspend fun register(request: RegisterUserRequest): ResourceResult<Success<RegisterUserResponse>> {
        return try {
            service.register(request).getResult()
        } catch (e: Throwable) {
            ResourceResult.Failure(Error(e.message, e))
        }
    }

}