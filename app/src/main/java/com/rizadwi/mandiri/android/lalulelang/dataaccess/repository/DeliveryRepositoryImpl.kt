package com.rizadwi.mandiri.android.lalulelang.dataaccess.repository

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.DeliveryRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject

class DeliveryRepositoryImpl @Inject constructor(private val remote: DeliveryRemoteDataSource) :
    DeliveryRepository {
    override suspend fun create(
        token: String,
        purchaseId: String,
        data: CreateDeliveryRequest
    ): ResourceResult<Success<CreateDeliveryResponse>> {
        return remote.create(token, purchaseId, data)
    }

    override suspend fun getCourierList(token: String): ResourceResult<Success<List<CourierResponse>>> {
        return remote.getCourierList(token)
    }
}