package com.rizadwi.mandiri.android.lalulelang.usecase.auth

import com.rizadwi.mandiri.android.lalulelang.data.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SaveTokenUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(token: String): ResourceResult<Unit> {
        return userRepository.saveToken(token)
    }
}