package com.rizadwi.mandiri.android.lalulelang.util.data

import com.google.gson.Gson
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auction.AuctionResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.LoginResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.auth.RegisterUserResponse
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.base.Success
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.bid.BidResponse
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
        val img = when (data.auction.topic) {
            "Gadget" -> "https://i.pinimg.com/originals/ca/42/81/ca42818475dc3b69641d6bda0c53e158.jpg"
            "Kendaraan" -> "https://i.pinimg.com/originals/6c/f2/b2/6cf2b2be3f26e3c7dc68b9ccca3cd142.jpg"
            else -> "https://png.pngtree.com/png-clipart/20190520/original/pngtree-question-mark-vector-icon-png-image_4017381.jpg"
        }

        return BidModel(
            id = data.id,
            image = img,
            name = data.auction.name,
            status = data.status,
            description = data.auction.description,
            amount = data.price,
            deadline = data.auction.endAt,
        )
    }

}