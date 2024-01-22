package com.rizadwi.mandiri.android.lalulelang.usecase.bid

import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.AuctionRepository
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.util.data.DataMapper
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetDetailAuctionUseCase @Inject constructor(
    private val mapper: DataMapper,
    private val auctionRepository: AuctionRepository
) {
    suspend fun invoke(token: String, id: String): ResourceResult<AuctionModel> {
        return when (val result = auctionRepository.getAuctionById(token, id)) {
            is ResourceResult.Failure -> result
            is ResourceResult.Success -> ResourceResult.Success(mapper.toAuctionModel(result.payload.data!!))
        }
    }
}