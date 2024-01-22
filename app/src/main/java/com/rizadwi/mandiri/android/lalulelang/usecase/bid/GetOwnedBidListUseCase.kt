package com.rizadwi.mandiri.android.lalulelang.usecase.bid

import com.rizadwi.mandiri.android.lalulelang.dataaccess.repository.BidRepository
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.util.data.DataMapper
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetOwnedBidListUseCase @Inject constructor(
    private val mapper: DataMapper,
    private val bidRepo: BidRepository,
) {
    suspend fun invoke(token: String): ResourceResult<List<BidModel>> {
        return when (val it = bidRepo.getMyBidList(token)) {
            is ResourceResult.Failure -> it
            is ResourceResult.Success -> ResourceResult.Success(it.payload.data!!.map(mapper::toBidModel))
        }
    }
}