package com.rizadwi.mandiri.android.lalulelang.usecase.auth

import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserRequest
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(data: RegisterUserRequest): ResourceResult<Success<RegisterUserResponse>> {
        return userRepository.register(data)
    }
}