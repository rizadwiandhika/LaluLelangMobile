package com.rizadwi.mandiri.android.lalulelang.mapper

import com.google.gson.Gson
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.data.model.bid.BidResponse
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.model.LoginModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataMapper @Inject constructor(private val gson: Gson) {
    fun toLoginModel(data: LoginResponse): LoginModel {
        return LoginModel(data.token, data.role)
    }

    fun toString(data: Success<RegisterUserResponse>): String {
        return data.message
    }

    fun toAuctionModel(data: AuctionResponse): AuctionModel {
        return AuctionModel(
            id = data.id,
            image = R.drawable.illustration_auction,
            name = data.name,
            topic = data.topic,
            description = data.description,
            price = data.price,
            deadline = data.endAt
        )
    }

    fun toBidModel(data: BidResponse): BidModel {
        return BidModel(
            id = data.id,
            image = R.drawable.illustration_auction,
            name = data.auction.name,
            status = data.status,
            description = data.auction.description,
            amount = data.price,
            deadline = data.auction.endAt,
        )
    }

}