package com.rizadwi.mandiri.android.lalulelang.dataaccess.remote

import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.TOKEN_PREFIX
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.service.LaluLelangService
import com.rizadwi.mandiri.android.lalulelang.util.ErrorCatcher
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.extension.getResult
import java.util.Calendar
import javax.inject.Inject

class AuctionRemoteDataSourceImpl @Inject constructor(
    private val service: LaluLelangService,
    private val c: ErrorCatcher
) :
    AuctionRemoteDataSource {
    override suspend fun getAuctions(token: String): ResourceResult<Success<List<AuctionResponse>>> {
        return c.catchIfError {
            when (val response = service.getAuctions(TOKEN_PREFIX + token).getResult()) {
                is ResourceResult.Failure -> response
                is ResourceResult.Success -> {
                    val availableAuction =
                        response.payload.data!!.filter { it.endAt.after(Calendar.getInstance().time) }

                    ResourceResult.Success(
                        Success(
                            response.payload.message,
                            response.payload.statusCode,
                            availableAuction
                        )
                    )
                }
            }
        }
    }

    override suspend fun getAuctionById(
        token: String,
        id: String
    ): ResourceResult<Success<AuctionResponse>> {
        return c.catchIfError { service.getAuctionById(TOKEN_PREFIX + token, id).getResult() }
    }


}