package com.rizadwi.mandiri.android.lalulelang.viewmodel.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.usecase.auth.GetTokenUseCase
import com.rizadwi.mandiri.android.lalulelang.usecase.home.GetListAuctionUseCase
import com.rizadwi.mandiri.android.lalulelang.util.data.ResourceResult
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.util.extension.postError
import com.rizadwi.mandiri.android.lalulelang.util.extension.postLoading
import com.rizadwi.mandiri.android.lalulelang.util.extension.postSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getListAuctionUseCase: GetListAuctionUseCase
) : ViewModel() {
    private var auctions: List<AuctionModel> = mutableListOf()

    private val _auctionLiveData: MutableLiveData<UIState<List<AuctionModel>>> = MutableLiveData()

    // https://stackoverflow.com/a/70754090/12407169
    private val _flow = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            _flow.debounce(350).collect(::filterAuction)
        }
    }

    val auctionLiveData: LiveData<UIState<List<AuctionModel>>> get() = _auctionLiveData

    fun fetchListAuction() = viewModelScope.launch {
        _auctionLiveData.postLoading()

        when (val result = getListAuctionUseCase.invoke(getTokenUseCase.invoke())) {
            is ResourceResult.Failure -> {
                _auctionLiveData.postError(result.cause)
                Log.d("riza", "Get list auction error: ${result.cause.message}")
            }

            is ResourceResult.Success -> {
                auctions = result.payload
                _auctionLiveData.postSuccess(auctions)
                Log.d("riza", "Get list auction success. data count: ${result.payload.size}")
            }
        }
    }

    fun prepareFilter(keyword: String) = viewModelScope.launch {
        _flow.emit(keyword)
    }

    private fun filterAuction(keyword: String) {
        if (keyword == "") {
            _auctionLiveData.postSuccess(auctions)
        } else {
            _auctionLiveData.postSuccess(auctions.filter(onNameAndTopic(keyword)))
        }
    }

    private fun onNameAndTopic(keyword: String): (AuctionModel) -> Boolean {
        return { it.name.contains(keyword, true) || it.topic.contains(keyword, true) }
    }

}