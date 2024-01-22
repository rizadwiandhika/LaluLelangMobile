package com.rizadwi.mandiri.android.lalulelang.data.remote

import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface AuctionRemoteDataSource {
    suspend fun getAuctions(token: String): ResourceResult<Success<List<AuctionResponse>>>
    suspend fun getAuctionById(token: String, id: String): ResourceResult<Success<AuctionResponse>>
}