package com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid

import com.google.gson.annotations.SerializedName
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction.AuctionResponse

data class BidResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("auction")
    val auction: AuctionResponse

)
