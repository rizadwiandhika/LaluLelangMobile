package com.rizadwi.mandiri.android.lalulelang.data.repository

import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.remote.AuctionRemoteDataSource
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import javax.inject.Inject

class AuctionRepositoryImpl @Inject constructor(private val remote: AuctionRemoteDataSource) :
    AuctionRepository {
    override suspend fun getAuction(token: String): ResourceResult<Success<List<AuctionResponse>>> {
        return remote.getAuctions(token)
    }

    override suspend fun getAuctionById(
        token: String,
        id: String
    ): ResourceResult<Success<AuctionResponse>> {
        return remote.getAuctionById(token, id)
    }
}