package com.rizadwi.mandiri.android.lalulelang.data.repository

import com.rizadwi.mandiri.android.lalulelang.data.local.UserLocalDataSource
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.remote.UserRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) :
    UserRepository {
    override suspend fun authenticate(data: LoginRequest): ResourceResult<Success<LoginResponse>> {
        return remoteDataSource.authenticate(data)
    }

    override suspend fun register(data: RegisterUserRequest): ResourceResult<Success<RegisterUserResponse>> {
        return remoteDataSource.register(data)
    }

    override suspend fun saveToken(token: String): ResourceResult<Unit> {
        return localDataSource.saveToken(token)
    }

    override suspend fun getToken(): ResourceResult<String> {
        return localDataSource.getToken()
    }

    override suspend fun deleteToken(): ResourceResult<Unit> {
        return localDataSource.deleteToken()
    }
}