package com.rizadwi.mandiri.android.lalulelang.data.remote

import android.util.Log
import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.service.LaluLelangService
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.extension.getResult
import javax.inject.Inject

class AuctionRemoteDataSourceImpl @Inject constructor(private val service: LaluLelangService) :
    AuctionRemoteDataSource {
    override suspend fun getAuctions(token: String): ResourceResult<Success<List<AuctionResponse>>> {
        return try {
            service.getAuctions(PREFIX + token).getResult()
        } catch (err: Throwable) {
            Log.d("riza", err.message ?: "Unknown error")
            ResourceResult.Failure(Error(err.message))
        }

    }

    companion object {
        const val PREFIX = "Bearer "
    }
}