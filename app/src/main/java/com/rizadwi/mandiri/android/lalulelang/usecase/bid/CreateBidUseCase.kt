package com.rizadwi.mandiri.android.lalulelang.usecase.bid

import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.BidRepository
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateBidUseCase @Inject constructor(private val repository: BidRepository) {
    suspend fun invoke(
        token: String,
        auctionId: String,
        data: BidRequest
    ): ResourceResult<Success<BidResponse>> {
        return repository.create(token, auctionId, data)
    }
}