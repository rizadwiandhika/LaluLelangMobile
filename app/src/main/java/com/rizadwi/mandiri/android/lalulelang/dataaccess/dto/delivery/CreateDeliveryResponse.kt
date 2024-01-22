package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery

import com.google.gson.annotations.SerializedName
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse

data class CreateDeliveryResponse(
    @SerializedName("address")
    val address: String,

    @SerializedName("courier")
    val courier: CourierResponse
)
