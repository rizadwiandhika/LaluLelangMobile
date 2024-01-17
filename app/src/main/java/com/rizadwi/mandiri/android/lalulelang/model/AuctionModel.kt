package com.rizadwi.mandiri.android.lalulelang.model

import java.util.Date


data class AuctionModel(
    val image: Int,
    val name: String,
    val topic: String,
    val description: String,
    val price: Int,
    val deadline: Date
)
