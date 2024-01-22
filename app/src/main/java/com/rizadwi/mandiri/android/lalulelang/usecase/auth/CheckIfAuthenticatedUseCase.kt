package com.rizadwi.mandiri.android.lalulelang.usecase.auth

import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.UserRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CheckIfAuthenticatedUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun invoke(): Boolean {
        return when (val result = repository.getToken()) {
            is ResourceResult.Failure -> false
            is ResourceResult.Success -> result.payload != ""
        }
    }
}