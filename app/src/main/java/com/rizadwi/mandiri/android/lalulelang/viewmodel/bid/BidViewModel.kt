package com.rizadwi.mandiri.android.lalulelang.viewmodel.bid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidRequest
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.GetTokenUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.bid.CreateBidUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.bid.GetDetailAuctionUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.bid.GetOwnedBidListUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.delivery.GetCourierListUseCase
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.extension.postError
import com.rizadwi.mandiri.android.lalulelang.util.extension.postLoading
import com.rizadwi.mandiri.android.lalulelang.util.extension.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BidViewModel @Inject constructor(
    private val getCourierListUseCase: GetCourierListUseCase,
    private val getOwnedBidListUseCase: GetOwnedBidListUseCase,
    private val getDetailAuctionUseCase: GetDetailAuctionUseCase,
    private val createBidUseCase: CreateBidUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _courierListLiveData: MutableLiveData<UIState<List<CourierResponse>>> =
        MutableLiveData()
    private val _bidListLiveData: MutableLiveData<UIState<List<BidModel>>> = MutableLiveData()
    private val _purchaseListLiveData: MutableLiveData<UIState<List<BidModel>>> = MutableLiveData()
    private val _auctionLiveData: MutableLiveData<UIState<AuctionModel>> = MutableLiveData()
    private val _createBidLiveData: MutableLiveData<UIState<Unit>> = MutableLiveData()

    val courierListLiveData: LiveData<UIState<List<CourierResponse>>> get() = _courierListLiveData
    val bidListLiveData: LiveData<UIState<List<BidModel>>> get() = _bidListLiveData
    val purchaseListLiveData: LiveData<UIState<List<BidModel>>> get() = _purchaseListLiveData
    val auctionLiveData: LiveData<UIState<AuctionModel>> get() = _auctionLiveData
    val createBidLiveData: LiveData<UIState<Unit>> get() = _createBidLiveData

    private lateinit var bidList: List<BidModel>

    fun fetchCourierLIst() = viewModelScope.launch {
        _courierListLiveData.postLoading()
        when (val it = getCourierListUseCase.invoke(getTokenUseCase.invoke())) {
            is ResourceResult.Failure -> _courierListLiveData.postError(it.cause)
            is ResourceResult.Success -> _courierListLiveData.postSuccess(it.payload.data!!)
        }
    }

    fun fetchAuctionById(id: String) = viewModelScope.launch {
        _auctionLiveData.postLoading()
        when (val it = getDetailAuctionUseCase.invoke(getTokenUseCase.invoke(), id)) {
            is ResourceResult.Failure -> _auctionLiveData.postError(it.cause)
            is ResourceResult.Success -> _auctionLiveData.postSuccess(it.payload)
        }
    }

    fun fetchAllMyBid() = viewModelScope.launch {
        _bidListLiveData.postLoading()
        _purchaseListLiveData.postLoading()
        when (val it = getOwnedBidListUseCase.invoke(getTokenUseCase.invoke())) {
            is ResourceResult.Failure -> {
                _bidListLiveData.postError(it.cause)
                _purchaseListLiveData.postError(it.cause)
            }

            is ResourceResult.Success -> {
                bidList = it.payload
                _bidListLiveData.postSuccess(bidList.filter { it.status != "WINNER" })
                _purchaseListLiveData.postSuccess(bidList.filter { it.status == "WINNER" })
            }
        }
    }


    fun createBid(amount: String, auctionId: String) = viewModelScope.launch {
        _createBidLiveData.postLoading()

        try {
            amount.toInt()
        } catch (nfe: NumberFormatException) {
            _createBidLiveData.postError(Error(nfe.message))
            return@launch
        }

        val token = getTokenUseCase.invoke()
        when (val it = createBidUseCase.invoke(token, auctionId, BidRequest(amount))) {
            is ResourceResult.Failure -> _createBidLiveData.postError(it.cause)
            is ResourceResult.Success -> _createBidLiveData.postSuccess(Unit)
        }
    }
}