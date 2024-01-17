package com.rizadwi.mandiri.android.lalulelang.usecase.home

import com.rizadwi.mandiri.android.lalulelang.data.repository.AuctionRepository
import com.rizadwi.mandiri.android.lalulelang.mapper.DataMapper
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetListAuctionUseCase @Inject constructor(
    private val mapper: DataMapper,
    private val repository: AuctionRepository
) {
    suspend fun invoke(token: String): ResourceResult<List<AuctionModel>> {
        return when (val result = repository.getAuction(token)) {
            is ResourceResult.Failure -> result
            is ResourceResult.Success -> ResourceResult.Success(
                result.payload.data!!.map(mapper::toAuctionModel)
            )
        }
    }
}