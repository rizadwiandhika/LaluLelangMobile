package com.rizadwi.mandiri.android.lalulelang.dataaccess.repository

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.remote.BidRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject

class BidRepositoryImpl @Inject constructor(private val remote: BidRemoteDataSource) :
    BidRepository {
    override suspend fun getMyBidList(token: String): ResourceResult<Success<List<BidResponse>>> {
        return remote.getBidList(token)
    }

    override suspend fun create(
        token: String,
        auctionId: String,
        data: BidRequest
    ): ResourceResult<Success<BidResponse>> {
        return remote.create(token, auctionId, data)
    }
}