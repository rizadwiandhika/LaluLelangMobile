package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface BidRemoteDataSource {
    suspend fun create(
        token: String,
        auctionId: String,
        data: BidRequest
    ): ResourceResult<Success<BidResponse>>

    suspend fun getBidList(token: String): ResourceResult<Success<List<BidResponse>>>
}