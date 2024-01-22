package com.rizadwi.mandiri.android.lalulelang.viewmodel.home

import android.os.Looper
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getListAuctionUseCase: GetListAuctionUseCase
) : ViewModel() {
    private val handlerOnType = android.os.Handler(Looper.getMainLooper())

    private var auctions: List<AuctionModel> = mutableListOf()

    private val _auctionLiveData: MutableLiveData<UIState<List<AuctionModel>>> = MutableLiveData()

    val auctionLiveData: LiveData<UIState<List<AuctionModel>>> get() = _auctionLiveData

    fun fetchListAuction() = viewModelScope.launch {
        _auctionLiveData.postLoading()

        delay(500L)
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
        handlerOnType.removeCallbacksAndMessages(null)
        handlerOnType.postDelayed({
            if (keyword == "") {
                _auctionLiveData.postSuccess(auctions)
            } else {
                _auctionLiveData.postSuccess(auctions.filter(onNameAndTopic(keyword)))
            }
        }, 350)
    }

    private fun onNameAndTopic(keyword: String): (AuctionModel) -> Boolean {
        return { it.name.contains(keyword, true) || it.topic.contains(keyword, true) }
    }

}