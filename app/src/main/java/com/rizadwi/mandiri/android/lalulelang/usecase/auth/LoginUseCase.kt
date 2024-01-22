package com.rizadwi.mandiri.android.lalulelang.usecase.auth

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(credential: LoginRequest): ResourceResult<Success<LoginResponse>> {
        return userRepository.authenticate(credential)
    }
}