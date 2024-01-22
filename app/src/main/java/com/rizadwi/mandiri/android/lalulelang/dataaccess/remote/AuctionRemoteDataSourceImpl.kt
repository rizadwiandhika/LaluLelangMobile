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

    override suspend fun getAuctionById(
        token: String,
        id: String
    ): ResourceResult<Success<AuctionResponse>> {
        return catchIfError { service.getAuctionById(PREFIX + token, id).getResult() }
    }

    private inline fun <reified T : Any> catchIfError(action: () -> ResourceResult<T>): ResourceResult<T> {
        return try {
            action.invoke()
        } catch (e: Throwable) {
            Log.d("riza", e.message ?: "Unknown error")
            ResourceResult.Failure(Error(e.message))
        }
    }


    companion object {
        const val PREFIX = "Bearer "
    }
}