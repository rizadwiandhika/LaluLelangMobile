package com.rizadwi.mandiri.android.lalulelang.usecase.delivery

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.DeliveryRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetCourierListUseCase @Inject constructor(private val repository: DeliveryRepository) {
    suspend fun invoke(token: String): ResourceResult<Success<List<CourierResponse>>> {
        return repository.getCourierList(token)
    }
}