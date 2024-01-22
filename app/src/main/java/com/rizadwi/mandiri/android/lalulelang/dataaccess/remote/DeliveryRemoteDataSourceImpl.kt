package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.TOKEN_PREFIX
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery.CreateDeliveryResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.service.LaluLelangService
import com.rizadwi.mandiri.android.lalulelang.util.ErrorCatcher
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.extension.getResult
import javax.inject.Inject

class DeliveryRemoteDataSourceImpl @Inject constructor(
    private val service: LaluLelangService,
    private val e: ErrorCatcher
) : DeliveryRemoteDataSource {
    override suspend fun create(
        token: String,
        purchaseId: String,
        data: CreateDeliveryRequest
    ): ResourceResult<Success<CreateDeliveryResponse>> {
        return e.catchIfError {
            service.createDelivery(TOKEN_PREFIX + token, purchaseId, data).getResult()
        }
    }

    override suspend fun getCourierList(token: String): ResourceResult<Success<List<CourierResponse>>> {
        return e.catchIfError { service.getCourierList(TOKEN_PREFIX + token).getResult() }
    }
}