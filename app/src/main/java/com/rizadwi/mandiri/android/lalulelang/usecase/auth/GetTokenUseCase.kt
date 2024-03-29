package com.rizadwi.mandiri.android.lalulelang.usecase.auth

import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetTokenUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(): String {
        return when (val result = userRepository.getToken()) {
            is ResourceResult.Failure -> ""
            is ResourceResult.Success -> result.payload
        }
    }
}