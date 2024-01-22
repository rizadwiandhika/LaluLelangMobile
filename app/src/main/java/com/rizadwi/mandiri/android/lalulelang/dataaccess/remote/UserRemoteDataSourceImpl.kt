package com.rizadwi.mandiri.android.lalulelang.data.remote

import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.service.LaluLelangService
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