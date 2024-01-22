package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.delivery

data class CreateDeliveryRequest(
    val shouldBeDeliveried: Boolean,
    val courierId: String,
    val address: String,
)