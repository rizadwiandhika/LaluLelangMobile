package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AuctionResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("topic")
    val topic: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("endAt")
    val endAt: Date

)