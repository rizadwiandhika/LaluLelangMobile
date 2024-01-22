package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.TOKEN_PREFIX
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.service.LaluLelangService
import com.rizadwi.mandiri.android.lalulelang.util.ErrorCatcher
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.extension.getResult
import javax.inject.Inject

class BidRemoteDataSourceImpl @Inject constructor(
    private val service: LaluLelangService,
    private val e: ErrorCatcher
) : BidRemoteDataSource {
    override suspend fun create(
        token: String,
        auctionId: String,
        data: BidRequest
    ): ResourceResult<Success<BidResponse>> {
        return e.catchIfError {
            service.createBid(TOKEN_PREFIX + token, auctionId, data).getResult()
        }
    }

    override suspend fun getBidList(token: String): ResourceResult<Success<List<BidResponse>>> {
        return e.catchIfError { service.getMyBidList(TOKEN_PREFIX + token).getResult() }
    }
}