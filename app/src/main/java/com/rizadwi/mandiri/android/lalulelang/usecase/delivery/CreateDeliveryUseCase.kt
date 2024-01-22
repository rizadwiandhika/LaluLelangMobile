package com.rizadwi.mandiri.android.lalulelang.usecase.delivery

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.DeliveryRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateDeliveryUseCase @Inject constructor(private val repository: DeliveryRepository) {
    suspend fun invoke(
        token: String,
        purchaseId: String,
        data: CreateDeliveryRequest
    ): ResourceResult<Success<CreateDeliveryResponse>> {
        return repository.create(token, purchaseId, data)
    }
}