package com.rizadwi.mandiri.android.lalulelang.dataaccess.repository

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface AuctionRepository {
    suspend fun getAuction(token: String): ResourceResult<Success<List<AuctionResponse>>>
    suspend fun getAuctionById(token: String, id: String): ResourceResult<Success<AuctionResponse>>
}