package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryResponse
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface DeliveryRemoteDataSource {
    suspend fun create(
        token: String,
        purchaseId: String,
        data: CreateDeliveryRequest
    ): ResourceResult<Success<CreateDeliveryResponse>>

    suspend fun getCourierList(token: String): ResourceResult<Success<List<CourierResponse>>>
}