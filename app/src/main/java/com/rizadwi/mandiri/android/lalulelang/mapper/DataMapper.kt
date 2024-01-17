package com.rizadwi.mandiri.android.lalulelang.mapper

import com.google.gson.Gson
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.data.model.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.data.model.base.Success
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
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
            image = R.drawable.ic_tv_outlined,
            name = data.name,
            topic = data.topic,
            description = data.description,
            price = data.price,
            deadline = data.endAt
        )
    }

}