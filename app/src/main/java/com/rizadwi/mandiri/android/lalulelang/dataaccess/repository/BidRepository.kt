package com.rizadwi.mandiri.android.lalulelang.dataaccess.repository

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult

interface BidRepository {

    suspend fun getMyBidList(token: String): ResourceResult<Success<List<BidResponse>>>

    suspend fun create(
        token: String,
        auctionId: String,
        data: BidRequest
    ): ResourceResult<Success<BidResponse>>

}